package ru.murashov.naumenjavacourse.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.murashov.naumenjavacourse.models.Purchase;
import ru.murashov.naumenjavacourse.models.User;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Integer> {

  List<Purchase> findAllByUser(User user);

}
