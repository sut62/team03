package SE62.team03.Entity;
import lombok.*;
import javax.persistence.*;
import java.util.*;
@Data
@Entity
@NoArgsConstructor
@Table(name="Location")
public class Location {
  @Id
  @SequenceGenerator(name="Location_SEQ",sequenceName="Location_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Location_SEQ")
  @Column(name="Location_id",unique = true, nullable = true)
  private @NonNull Long id;
  private @NonNull String name;


  @OneToMany(fetch = FetchType.EAGER)
  private Collection<ClubEvent> Event;
}