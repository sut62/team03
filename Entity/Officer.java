package SE62.team03.Entity;
import lombok.*;
import javax.persistence.*;
import java.util.*;
@Data
@Entity
@NoArgsConstructor
@Table(name="Officer")
public class Officer {
  @Id
  @SequenceGenerator(name="Officer_SEQ",sequenceName="Officer_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Officer_SEQ")
  @Column(name="Officer_id",unique = true, nullable = true)
  private @NonNull Long id;
  private @NonNull String name;

  @OneToMany(fetch = FetchType.EAGER)
  private Collection<ClubEvent> Event;

}