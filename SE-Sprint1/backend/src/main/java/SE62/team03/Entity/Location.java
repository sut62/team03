package SE62.team03.Entity;

import lombok.*;
import javax.persistence.*;
import java.util.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
@Entity
@NoArgsConstructor
@Table(name="Location")
public class Location {
  @Id
  @SequenceGenerator(name="Location_SEQ",sequenceName="Location_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Location_SEQ")
  @Column(name="Location_ID",unique = true, nullable = true)

  private 
  @NotNull Long id;

  // @Pattern(regexp = "\\D")  
  
  
  private 
  @Size (min = 4,max = 30)
  @NotNull String name;


  @OneToMany(fetch = FetchType.EAGER)
  private Collection<ClubEvent> Event;

  public void setName(String name) {
    this.name = name;
  }
}