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
@Table(name = "producer")
@Getter
@Setter
@NoArgsConstructor
public class Producer {

  @GeneratedValue
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @Basic
  @Column(name = "name")
  @NotEmpty(message = "Producer name should not be empty")
  @Size(min = 2, max = 50, message = "Producer name should be between 2 and 50 characters")
  private String name;

  public Producer(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Producer producer = (Producer) o;
    return Objects.equals(id, producer.id) && Objects.equals(name, producer.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "Producer{" + "name='" + name + '\'' + '}';
  }
}
