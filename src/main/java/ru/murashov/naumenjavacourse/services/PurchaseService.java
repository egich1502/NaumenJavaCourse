package ru.murashov.naumenjavacourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.murashov.naumenjavacourse.models.Purchase;
import ru.murashov.naumenjavacourse.repositories.PurchaseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseService {

  private final PurchaseRepository purchaseRepository;

  @Autowired
  public PurchaseService(PurchaseRepository purchaseRepository) {
    this.purchaseRepository = purchaseRepository;
  }

  public void savePurchase(Purchase purchase) {
    purchaseRepository.save(purchase);
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

  public void updatePurchase(int id, Purchase updatedPurchase){
    Purchase purchaseToBeUpdated = purchaseRepository.findById(id).get();
    purchaseToBeUpdated.setUser(updatedPurchase.getUser());
    purchaseToBeUpdated.setProduct(updatedPurchase.getProduct());
    purchaseRepository.save(purchaseToBeUpdated);
  }
}
