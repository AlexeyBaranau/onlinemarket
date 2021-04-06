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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"customer", "cartItemList"})
@Entity
@Table(name = "m_baskets")
public class Basket implements Serializable {

  private static final long serialVersionUID = 8032218948182052139L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "customer_id")
  @JsonBackReference
  private Customer customer;

//  @OneToMany(
//      mappedBy = "cart",
//      cascade = CascadeType.ALL,
//      fetch = FetchType.LAZY,
//      orphanRemoval = true)
//  @JsonManagedReference
//  private List<CartItem> cartItemList = new ArrayList<>();

  @Column(name = "total_price")
  private BigDecimal totalPrice;
}
