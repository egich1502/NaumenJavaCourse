package ru.murashov.naumenjavacourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.murashov.naumenjavacourse.models.User;
import ru.murashov.naumenjavacourse.services.UserService;

@Controller
public class MainController {

  private final UserService userService;

  @Autowired
  public MainController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("")
  public String getMainPage(Model model) {
    model.addAttribute("user", userService.getAuthenticatedUser());
    return "mainPage";
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
