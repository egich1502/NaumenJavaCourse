package ru.murashov.naumenjavacourse.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.murashov.naumenjavacourse.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

  User findByLogin(String login);
}

