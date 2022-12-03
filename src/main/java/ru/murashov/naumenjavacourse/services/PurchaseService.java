package ru.murashov.naumenjavacourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.murashov.naumenjavacourse.models.Purchase;
import ru.murashov.naumenjavacourse.models.User;
import ru.murashov.naumenjavacourse.repositories.PurchaseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {

  private final PurchaseRepository purchaseRepository;
  private final UserService userService;
  private final ProductService productService;

  @Autowired
  public PurchaseService(PurchaseRepository purchaseRepository,
                         UserService userService, ProductService productService) {
    this.purchaseRepository = purchaseRepository;
    this.userService = userService;
    this.productService = productService;
  }

  public void savePurchase(int productId) {
    User user = userService.getAuthenticatedUser();
    Purchase newPurchase = new Purchase();
    newPurchase.setUser(user);
    newPurchase.setProduct(productService.getProduct(productId));
    purchaseRepository.save(newPurchase);
  }

  public List<Purchase> getAllPurchase() {
    List<Purchase> purchases = new ArrayList<>();
    purchaseRepository.findAll().forEach(purchases::add);
    return purchases;
  }


  public Purchase getPurchase(int id) {
    return purchaseRepository.findById(id).get();
  }

  public void deletePurchase(int id) {
    purchaseRepository.deleteById(id);
  }

  public List<Purchase> getAllPurchasesByUser(User user){
    return purchaseRepository.findAllByUser(user);
  }
}
