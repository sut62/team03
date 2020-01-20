package SE62.team03.Entity;
import lombok.*;
import javax.persistence.*;
import java.util.*;
import javax.validation.constraints.NotNull;
@Data
@Entity
@NoArgsConstructor
@Table(name="Location")
public class Location {
  @Id
  @SequenceGenerator(name="Location_SEQ",sequenceName="Location_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Location_SEQ")
  @Column(name="Location_ID",unique = true, nullable = true)
  private @NotNull Long id;
  private @NotNull String name;


  @OneToMany(fetch = FetchType.EAGER)
  private Collection<ClubEvent> Event;

  public void setName(String name) {
    this.name = name;
  }
}