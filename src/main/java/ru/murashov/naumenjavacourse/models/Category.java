package ru.murashov.naumenjavacourse.models;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
public class Category {

  @GeneratedValue
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;
  @Basic
  @Column(name = "name")
  @NotEmpty(message = "Category name should not be empty")
  @Size(min = 2, max = 50, message = "Category name should be between 2 and 50 characters")
  private String name;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Category category = (Category) o;
    return Objects.equals(id, category.id) && Objects.equals(name, category.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
