package by.finalproject.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(exclude = "roles")
@Table(name = "m_customers")
@Builder
public class Customer implements Serializable {

  private static final long serialVersionUID = 723779660656994741L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String name;

  @Column private String surname;

  @Column
  @Enumerated(EnumType.STRING)
  private Gender gender = Gender.NOT_SELECTED;

  @Column private Timestamp created = new Timestamp(System.currentTimeMillis());

  @Column private Timestamp changed = new Timestamp(System.currentTimeMillis());

  @Column private String phone;

  @Column private String address;

  @Column private String login;

  @Column private String password;

  @Column(name = "is_deleted")
  private Boolean isDeleted = false;

  @OneToMany(
      mappedBy = "customer",
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER,
      orphanRemoval = true)
  @JsonManagedReference
  private Set<Role> roles = Collections.emptySet();

  @OneToOne(
      mappedBy = "customer",
      cascade = CascadeType.ALL,
      fetch = FetchType.LAZY,
      orphanRemoval = true)
  @JsonManagedReference
  private Basket basket;
}
