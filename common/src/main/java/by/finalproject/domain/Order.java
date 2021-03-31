package by.finalproject.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_orders")
public class Order implements Serializable {

  private static final long serialVersionUID = 1001588221863510188L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "total_price")
  private Double totalPrice;

  @Column (name = "product_id")
  private Long productId;

  @Column (name = "amount")
  private Integer amount;

  @Column(name = "customer_id")
  private Long customer;

  @Column private Timestamp created;

  //////
}
