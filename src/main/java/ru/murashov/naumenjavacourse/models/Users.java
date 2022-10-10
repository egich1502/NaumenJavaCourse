package ru.murashov.naumenjavacourse.models;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;
  @Basic
  @Column(name = "name", nullable = false, length = 50)
  private String name;
  @Basic
  @Column(name = "login", nullable = false, length = 30)
  private String login;
  @Basic
  @Column(name = "password", nullable = false, length = 30)
  private String password;
  @Basic
  @Column(name = "role", nullable = false, length = 20)
  private String role;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Users users = (Users) o;
    return Objects.equals(id, users.id) && Objects.equals(name, users.name)
        && Objects.equals(login, users.login) && Objects.equals(password,
        users.password) && Objects.equals(role, users.role);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, login, password, role);
  }
}
