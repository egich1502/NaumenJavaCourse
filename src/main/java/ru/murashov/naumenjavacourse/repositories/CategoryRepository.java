package ru.murashov.naumenjavacourse.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.murashov.naumenjavacourse.models.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
