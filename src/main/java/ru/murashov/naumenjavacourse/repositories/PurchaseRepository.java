package ru.murashov.naumenjavacourse.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.murashov.naumenjavacourse.models.Purchase;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {

}
