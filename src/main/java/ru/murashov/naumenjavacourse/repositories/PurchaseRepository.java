package ru.murashov.naumenjavacourse.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.murashov.naumenjavacourse.models.Producer;
import ru.murashov.naumenjavacourse.models.Purchase;

public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {
}
