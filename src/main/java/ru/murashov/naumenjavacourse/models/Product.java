package ru.murashov.naumenjavacourse.models;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
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
public class Product {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;
  @Basic
  @Column(name = "name", nullable = false, length = 50)
  private String name;
  @Basic
  @Column(name = "price", nullable = true)
  private Integer price;
  @Basic
  @Column(name = "description", nullable = true, length = 100)
  private String description;
  @Basic
  @Column(name = "producer_id", nullable = true)
  private Integer producerId;
  @Basic
  @Column(name = "category_id", nullable = true)
  private Integer categoryId;


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Product product = (Product) o;
    return Objects.equals(id, product.id) && Objects.equals(name, product.name)
        && Objects.equals(price, product.price) && Objects.equals(description,
        product.description) && Objects.equals(producerId, product.producerId)
        && Objects.equals(categoryId, product.categoryId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, price, description, producerId, categoryId);
  }
}
