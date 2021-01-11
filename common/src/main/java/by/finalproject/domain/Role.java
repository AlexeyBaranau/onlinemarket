package by.finalproject.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@EqualsAndHashCode(exclude = "customer")
@Table(name = "m_roles")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "role_name")
  @Enumerated(EnumType.STRING)
  private SystemRoles roleName;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id")
  @JsonBackReference
  private Customer customer;

  public Role(SystemRoles roleName, Customer customer) {
    this.roleName = roleName;
    this.customer = customer;
  }

  public Role() {}

  public Role(SystemRoles roleName) {
    this.roleName = roleName;
  }
}
