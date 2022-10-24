package ru.murashov.naumenjavacourse.config.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.murashov.naumenjavacourse.models.User;
import ru.murashov.naumenjavacourse.services.UserService;

@Controller
public class RegistrationController {

  private final UserService userService;

  @Autowired
  public RegistrationController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/registration")
  public String registration() {
    return "registration";
  }

  @PostMapping("/registration")
  public String addUser(User user, Model model) {
    try {
      System.out.println(user);
      userService.addUser(user);
      return "redirect:/login";
    } catch (Exception e) {
      model.addAttribute("message", "User exists");
      return "registration";
    }
  }

}
