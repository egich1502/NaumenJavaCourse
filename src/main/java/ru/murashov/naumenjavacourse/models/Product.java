package ru.murashov.naumenjavacourse.models;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {

  @GeneratedValue
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;
  @Basic
  @Column(name = "name", nullable = false, length = 50)
  private String name;
  @Basic
  @Column(name = "price", nullable = false)
  private Integer price;
  @Basic
  @Column(name = "description", length = 100)
  private String description;
  @ManyToOne
  @JoinColumn(name = "producer_id")
  private Producer producer;
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(
        price, product.price) && Objects.equals(description, product.description) && Objects.equals(
        producer, product.producer) && Objects.equals(category, product.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, price, description, producer, category);
  }
}
