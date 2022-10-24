package ru.murashov.naumenjavacourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.murashov.naumenjavacourse.models.Product;
import ru.murashov.naumenjavacourse.services.ProductService;

@Controller
@RequestMapping()
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/save")
    public String saveProduct() {
        return "product/save";
    }

    @PostMapping("/product/save")
    public String saveProduct(@ModelAttribute("product") Product product){
        productService.saveProduct(product);
        return "redirect:/product/getAll";
    }

    @GetMapping()
    public String getAllProducts(Model model) {
        model.addAttribute("allProducts", productService.getAllProducts());
        return "product/getAll";
    }

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.getProduct(id));
        return "product/get";
    }

    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("product", productService.getProduct(id));
        return "/product/edit";
    }

    @PatchMapping("/product/edit/{id}")
    public String updateProduct(@PathVariable("id") int id, @ModelAttribute("product") Product product){
        productService.updateProduct(id, product);
        return "redirect:/product/{id}";
    }
}
