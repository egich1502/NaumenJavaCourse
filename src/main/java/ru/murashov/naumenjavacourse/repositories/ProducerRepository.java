package ru.murashov.naumenjavacourse.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.murashov.naumenjavacourse.models.Producer;


@Repository
public interface ProducerRepository extends CrudRepository<Producer, Integer> {

}
