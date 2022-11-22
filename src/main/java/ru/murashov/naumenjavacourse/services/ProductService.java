package ru.murashov.naumenjavacourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.murashov.naumenjavacourse.models.Category;
import ru.murashov.naumenjavacourse.models.Producer;
import ru.murashov.naumenjavacourse.models.Product;
import ru.murashov.naumenjavacourse.repositories.ProductRepository;

import java.util.ArrayList;
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

  public List<Product> getAllProducts() {
    List<Product> products = new ArrayList<>();
    productRepository.findAll().forEach(products::add);
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

  public List<Product> getAllProductsByCategory(Category category) {
    return productRepository.findAllByCategory(category);
  }

  public List<Product> getAllProductsByProducer(Producer producer) {
    return productRepository.findAllByProducer(producer);
  }
}
