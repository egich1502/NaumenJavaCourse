package ru.murashov.naumenjavacourse.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.murashov.naumenjavacourse.models.Category;
import ru.murashov.naumenjavacourse.models.Producer;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
}
