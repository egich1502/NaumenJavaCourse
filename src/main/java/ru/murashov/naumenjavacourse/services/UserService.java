package ru.murashov.naumenjavacourse.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.murashov.naumenjavacourse.models.Role;
import ru.murashov.naumenjavacourse.models.User;
import ru.murashov.naumenjavacourse.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User myUser = userRepository.findByLogin(username);
    return new org.springframework.security.core.userdetails.User(myUser.getUsername(),
        myUser.getPassword(), mapRolesToAuthorities(myUser.getRole()));
  }

  private List<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
    return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.name()))
        .collect(Collectors.toList());
  }

  public void addUser(User user) throws Exception {
    User userFromDB = userRepository.findByLogin(user.getLogin());
    if (userFromDB != null) {
      throw new Exception("user Exist");
    }
    user.setRole(Collections.singleton(Role.USER));
    user.setPassword(encoder.encode(user.getPassword()));
    userRepository.save(user);
  }

  public List<User> getAllUsers() {
    List<User> users = new ArrayList<>();
    userRepository.findAll().forEach(users::add);
    return users;
  }

  public User getUserById(int id) {
    return userRepository.findById(id).get();
  }

  public void updateUserRole(int id, User user) {
    User userToBeUpdated = userRepository.findById(id).get();
    userToBeUpdated.setRole(user.getRole()
    );
    userRepository.save(userToBeUpdated);
  }

  public User getAuthenticatedUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    String currentUser = auth.getName();
    return userRepository.findByUsername(currentUser);
  }

  public void deleteUser(int id) {
    userRepository.deleteById(id);
  }

}
