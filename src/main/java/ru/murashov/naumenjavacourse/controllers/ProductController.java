package ru.murashov.naumenjavacourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.murashov.naumenjavacourse.models.Product;
import ru.murashov.naumenjavacourse.services.ProductService;
import ru.murashov.naumenjavacourse.services.UserService;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

  private final ProductService productService;
  private final UserService userService;


  @Autowired
  public ProductController(ProductService productService, UserService userService) {
    this.productService = productService;
    this.userService = userService;
  }

  @Secured("ROLE_ADMIN")
  @GetMapping("save")
  public String saveProduct() {
    return "product/save";
  }

  @Secured("ROLE_ADMIN")
  @PostMapping("save")
  public String saveProduct(@ModelAttribute("product") Product product) {
    productService.saveProduct(product);
    return "redirect:/product/getAll";
  }

  @GetMapping("getAll")
  public String getAllProducts(Model model) {
    model.addAttribute("allProducts", productService.getAllProducts());
    return "product/getAll";
  }

  @GetMapping("{id}")
  public String getProduct(@PathVariable("id") int id, Model model) {
    model.addAttribute("product", productService.getProduct(id));
    return "product/get";
  }

  @Secured("ROLE_ADMIN")
  @GetMapping("{id}/edit")
  public String editProduct(@PathVariable("id") int id, Model model) {
    model.addAttribute("product", productService.getProduct(id));
    return "product/edit";
  }

  @Secured("ROLE_ADMIN")
  @PatchMapping("{id}/edit")
  public String updateProduct(@PathVariable("id") int id,
      @ModelAttribute("product") Product product) {
    productService.updateProduct(id, product);
    return "redirect:/product/{id}";
  }

  @Secured("ROLE_ADMIN")
  @DeleteMapping("/{id}/delete")
  public String deleteProduct(@PathVariable("id") int id) {
    productService.deleteProduct(id);
    return "redirect:/product/getAll";
  }

  @Secured({"ROLE_USER", "ROLE_ADMIN"})
  @PostMapping("/{id}/buy")
  public String buyProduct(@PathVariable("id") int id) {
    userService.makePurchase(id);
    return "redirect:/product/{id}";
  }

  @GetMapping("/filter/{key}")
  public String filterProduct(@PathVariable("key") int key, Model model){
    List<Product> allProducts = productService.getAllProducts();
    if (key == -1) Collections.sort(allProducts, Collections.reverseOrder());
    else Collections.sort(allProducts);
    model.addAttribute("allProducts", allProducts);
    return "/product/getAll";
  }
}
