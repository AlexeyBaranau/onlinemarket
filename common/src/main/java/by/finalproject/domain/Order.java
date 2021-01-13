package by.finalproject.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "m_orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "total_price")
  private Double totalPrice;

  @Column private int amount;

  @Column(name = "customer_id")
  private Long customerId;

  @Column(name = "product_id")
  private Long productId;

  @Column
  private Timestamp created = new Timestamp(System.currentTimeMillis());

  @Column
  private Timestamp changed = new Timestamp(System.currentTimeMillis());
}
