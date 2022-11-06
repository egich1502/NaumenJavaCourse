package ru.murashov.naumenjavacourse.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.murashov.naumenjavacourse.models.Role;
import ru.murashov.naumenjavacourse.models.User;
import ru.murashov.naumenjavacourse.services.UserService;

@Controller
@RequestMapping("user")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("getAll")
  public String getAllUsers(Model model) {
    model.addAttribute("allUsers", userService.getAllUsers());
    return "user/getAll";
  }

  @GetMapping("{id}")
  public String getUser(@PathVariable("id") int id, Model model) {
    model.addAttribute("user", userService.getUserById(id));
    return "user/get";
  }

  @Secured("ROLE_ADMIN")
  @GetMapping("{id}/edit")
  public String editUserRole(@PathVariable("id") int id, Model model) {
    model.addAttribute("user", userService.getUserById(id));
    return "user/edit";
  }

  @Secured("ROLE_ADMIN")
  @PatchMapping("{id}/edit")
  public String updateUserRole(@PathVariable("id") int id,
      @ModelAttribute("user") User user) {
    userService.updateUserRole(id, user);
    return "redirect:/user/{id}";
  }

  @Secured("ROLE_ADMIN")
  @DeleteMapping("{id}/delete")
  public String deleteUser(@PathVariable("id") int id) {
    userService.deleteUser(id);
    return "redirect:/user/getAll";
  }

  @ModelAttribute("roles")
  public List<Role> getRoles() {
    List<Role> roles = new ArrayList<>();
    roles.add(Role.USER);
    roles.add(Role.ADMIN);
    return roles;
  }
}
