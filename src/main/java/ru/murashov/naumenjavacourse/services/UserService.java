package ru.murashov.naumenjavacourse.services;

import java.util.Collections;
import java.util.List;
import java.util.Set;
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

}
