package ru.murashov.naumenjavacourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.murashov.naumenjavacourse.models.Discoveries;
import ru.murashov.naumenjavacourse.models.Product;
import ru.murashov.naumenjavacourse.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public void saveProduct(Product product) {
    productRepository.save(product);
  }

  public List<Product> getAllProducts(Discoveries discoveries) {
    List<Product> products = new ArrayList<>();
    productRepository.findAll().forEach(products::add);
    products = products.stream().filter(element ->
            element.getPrice() >= discoveries.getMin() &&
                    element.getPrice() <= discoveries.getMax()).toList();
    return products;
  }


  public Product getProduct(int id) {
    return productRepository.findById(id).get();
  }

  public void updateProduct(int id, Product updatedProduct) {
    Product productToBeUpdate = productRepository.findById(id).get();
    productToBeUpdate.setName(updatedProduct.getName());
    productToBeUpdate.setPrice(updatedProduct.getPrice());
    productToBeUpdate.setDescription(updatedProduct.getDescription());
    productRepository.save(productToBeUpdate);
  }

  public void deleteProduct(int id) {
    productRepository.deleteById(id);
  }

  public List<Product> getSortProduct(int key, Discoveries discoveries){
    List<Product> allProducts = getAllProducts(discoveries);
    if (key == -1)
      allProducts.sort(Collections.reverseOrder());
    else
      allProducts = allProducts.stream().sorted().toList();
    return allProducts;
  }
}
