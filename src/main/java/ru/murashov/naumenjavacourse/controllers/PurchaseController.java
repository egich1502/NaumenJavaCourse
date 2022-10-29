package ru.murashov.naumenjavacourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.murashov.naumenjavacourse.models.Purchase;
import ru.murashov.naumenjavacourse.services.PurchaseService;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

  private final PurchaseService purchaseService;

  @Autowired
  public PurchaseController(PurchaseService purchaseService) {
    this.purchaseService = purchaseService;
  }

  @GetMapping("/save")
  public String savePurchase() {
    return "/purchase/save";
  }

  @PostMapping("/save")
  public String savePurchase(@ModelAttribute("purchase") Purchase purchase) {
    purchaseService.savePurchase(purchase);
    return "redirect:/purchase/getAll";
  }

  @GetMapping("/getAll")
  public String getAllPurchases(Model model) {
    model.addAttribute("allPurchases", purchaseService.getAllPurchase());
    return "purchase/getAll";
  }

  @GetMapping("/{id}")
  public String getPurchase(@PathVariable("id") int id, Model model) {
    model.addAttribute("purchase", purchaseService.getPurchase(id));
    return "purchase/get";
  }

  @DeleteMapping("/delete/{id}")
  public String deletePurchase(@PathVariable("id") int id) {
    purchaseService.deletePurchase(id);
    return "redirect:/purchase/getAll";
  }

  @GetMapping("/edit/{id}")
  public String editPurchase(@PathVariable("id") int id, Model model){
    model.addAttribute(purchaseService.getPurchase(id));
    return "/purchase/edit";
  }

  @PatchMapping("/edit/{id}")
  public String editPurchase(@PathVariable("id") int id, @ModelAttribute("purchase") Purchase purchase){
    purchaseService.updatePurchase(id, purchase);
    return "redirect:/purchase/getAll";
  }
}
