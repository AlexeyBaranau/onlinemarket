package by.finalproject.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(exclude = "roles")
@Table(name = "m_customers")
public class Customer {

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
}
