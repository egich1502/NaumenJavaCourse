package ru.murashov.naumenjavacourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.murashov.naumenjavacourse.services.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController {

  private final CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @Secured("ROLE_ADMIN")
  @GetMapping("save")
  public String saveCategory() {
    return "/category/save";
  }

  @Secured("ROLE_ADMIN")
  @PostMapping("save")
  public String saveCategory(String name) {
    categoryService.saveCategory(name);
    return "redirect:/category/getAll";
  }

  @GetMapping("{id}")
  public String getCategory(@PathVariable("id") int id, Model model) {
    model.addAttribute("category", categoryService.getCategory(id));
    return "/category/get";
  }

  @GetMapping("getAll")
  public String getAllCategory(Model model) {
    model.addAttribute("allCategories", categoryService.getAllCategory());
    return "/category/getAll";
  }
}
