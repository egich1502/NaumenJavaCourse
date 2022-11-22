package ru.murashov.naumenjavacourse.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.murashov.naumenjavacourse.models.Category;
import ru.murashov.naumenjavacourse.models.Producer;
import ru.murashov.naumenjavacourse.models.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

  List<Product> findAllByCategory(Category category);

  List<Product> findAllByProducer(Producer producer);
}
