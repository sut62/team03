package SE62.team03.Entity;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="Clubs")
public class Clubs {
  @Id
  @SequenceGenerator(name="Clubs_SEQ",sequenceName="Clubs_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Clubs_SEQ")
  @Column(name="Clubs_id",unique = true, nullable = true)
  private @NonNull Long id;
  private @NonNull String name;

  @OneToMany(fetch = FetchType.EAGER)
  private Collection<ClubEvent> Event;

}