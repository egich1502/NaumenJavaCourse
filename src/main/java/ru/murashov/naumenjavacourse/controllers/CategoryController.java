package ru.murashov.naumenjavacourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.murashov.naumenjavacourse.models.Category;
import ru.murashov.naumenjavacourse.services.CategoryService;

import javax.validation.Valid;

@Controller
@RequestMapping("/category")
public class CategoryController {

  private final CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("/save")
  public String saveCategory(@ModelAttribute("category") Category category) {
    return "/category/save";
  }

  @PostMapping("/save")
  public String saveCategory(@ModelAttribute("category") @Valid Category category, BindingResult bindingResult) {
    if (bindingResult.hasErrors())
      return "category/getAll";
    categoryService.saveCategory(category);
    return "redirect:/category/getAll";
  }

  @GetMapping("/{id}")
  public String getCategory(@PathVariable("id") int id, Model model) {
    model.addAttribute("category", categoryService.getCategory(id));
    return "/category/get";
  }

  @GetMapping("/getAll")
  public String getAllCategory(Model model) {
    model.addAttribute("allCategories", categoryService.getAllCategory());
    return "/category/getAll";
  }

  @DeleteMapping("/delete/{id}")
  public String deleteCategory(@PathVariable("id") int id){
    categoryService.deleteCategory(id);
    return "redirect:/category/getAll";
  }

  @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") int id, Model model){
    model.addAttribute("category", categoryService.getCategory(id));
    return "/category/edit";
  }

  @PatchMapping("/edit/{id}")
  public String editCategory(@ModelAttribute("category") @Valid Category category, BindingResult bindingResult,
                             @PathVariable("id") int id){
    if (bindingResult.hasErrors())
      return "category/edit";
    categoryService.updateCategory(id, category);
    return "redirect:/category/{id}";
  }
}
