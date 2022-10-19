package ru.murashov.naumenjavacourse.models;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "purchase")
@Getter
@Setter
@NoArgsConstructor
public class Purchase {

  @GeneratedValue
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;
  @Basic
  @Column(name = "user_id", nullable = false)
  private Integer userId;
  @Basic
  @Column(name = "product_id", nullable = false)
  private Integer productId;


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Purchase purchase = (Purchase) o;
    return Objects.equals(id, purchase.id) && Objects.equals(userId,
        purchase.userId) && Objects.equals(productId, purchase.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, productId);
  }
}
