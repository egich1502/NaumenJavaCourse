package ru.murashov.naumenjavacourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.murashov.naumenjavacourse.models.Category;
import ru.murashov.naumenjavacourse.models.Product;
import ru.murashov.naumenjavacourse.models.Purchase;
import ru.murashov.naumenjavacourse.repositories.CategoryRepository;
import ru.murashov.naumenjavacourse.repositories.ProductRepository;
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

    public Purchase savePurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
        return purchase;
    }

    public List<Purchase> getAllPurchase() {
        List<Purchase> purchases = new ArrayList<>();
        purchaseRepository.findAll().forEach(purchases::add);
        return purchases;
    }


    public Purchase getPurchase(int id){
        Purchase category = purchaseRepository.findById(id).get();
        return category;
    }

    public void deletePurchase(int id){
        purchaseRepository.deleteById(id);
    }
}
