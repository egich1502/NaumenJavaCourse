package ru.murashov.naumenjavacourse.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.murashov.naumenjavacourse.models.Product;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
