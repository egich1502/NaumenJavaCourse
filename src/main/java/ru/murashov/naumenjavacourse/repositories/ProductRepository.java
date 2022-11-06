package ru.murashov.naumenjavacourse.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.murashov.naumenjavacourse.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

}
