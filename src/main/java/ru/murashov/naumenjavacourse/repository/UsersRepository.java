package ru.murashov.naumenjavacourse.repository;

import org.springframework.data.repository.CrudRepository;
import ru.murashov.naumenjavacourse.models.Users;

public interface UsersRepository extends CrudRepository<Users, Integer> {

}
