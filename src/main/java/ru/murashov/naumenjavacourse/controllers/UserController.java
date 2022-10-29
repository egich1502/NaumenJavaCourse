package ru.murashov.naumenjavacourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.murashov.naumenjavacourse.models.User;
import ru.murashov.naumenjavacourse.services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id, Model model){
        model.addAttribute(userService.getUser(id));
        return "/user/get";
    }

    @GetMapping("/getAll")
    public String getAllUsers(Model model){
        model.addAttribute("allUsers", userService.getAllUsers());
        return "/user/getAll";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return "redirect:/user/getAll";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
        model.addAttribute(userService.getUser(id));
        return "/user/edit";
    }

    @PatchMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, @ModelAttribute("user") @Valid User user,
                           BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "user/edit";
        userService.updateUser(id, user);
        return "redirect:/user/getAll";
    }
}
