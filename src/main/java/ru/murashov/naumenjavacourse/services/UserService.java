package ru.murashov.naumenjavacourse.services;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.murashov.naumenjavacourse.models.Role;
import ru.murashov.naumenjavacourse.models.User;
import ru.murashov.naumenjavacourse.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {

  private final UserRepository userRepository;

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
    User userFromDB = userRepository.findByLogin(user.getUsername());
    if (userFromDB != null) {
      throw new Exception("user Exist");
    }
    user.setRole(Collections.singleton(Role.USER));
    userRepository.save(user);
  }

  public User getUser(int id){
    return userRepository.findById(id).get();
  }

  public List<User> getAllUsers(){
    List<User> users = new ArrayList<User>();
    userRepository.findAll().forEach(users::add);
    return users;
  }

  public void deleteUser(int id){
    userRepository.deleteById(id);
  }

  public void updateUser(int id, User updatedUser){
    User userToBeUpdated = userRepository.findById(id).get();
    userToBeUpdated.setPassword(updatedUser.getPassword());
    userToBeUpdated.setLogin(updatedUser.getLogin());
    userToBeUpdated.setUsername(updatedUser.getUsername());
    userRepository.save(userToBeUpdated);

  }
}
