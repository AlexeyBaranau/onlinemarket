package by.finalproject.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_categories")
@EqualsAndHashCode(exclude = "subcategories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column private String name;

  @OneToMany(
          mappedBy = "category",
          cascade = CascadeType.ALL,
          fetch = FetchType.EAGER,
          orphanRemoval = true
  )
  @JsonManagedReference
  Set<SubCategory> subCategorySet = new HashSet<>();
}
