package by.finalproject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"basket", "product"})
@Entity
@Table(name = "m_baskets_items")
public class BasketItem implements Serializable {

  private static final long serialVersionUID = 5160599951385751108L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "basket_id")
  @JsonBackReference
  private Basket basket;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @Column(name = "amount")
  private Integer amount;
}
