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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
  @Column(name = "name")
  @NotEmpty(message = "Product name should not be empty")
  @Size(min = 2, max = 50, message = "Product name should be between 2 and 50 characters")
  private String name;
  @Basic
  @Column(name = "price", nullable = false)
  @Min(value = 0, message = "Price should be greater than 0")
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
