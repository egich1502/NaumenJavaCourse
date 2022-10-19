package ru.murashov.naumenjavacourse.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.murashov.naumenjavacourse.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
  User findByLogin(String login);
}

