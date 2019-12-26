package SE62.team03.Entity;
import lombok.*;
import javax.persistence.*;
import java.util.*;
@Data
@Entity
@NoArgsConstructor
@Table(name="Employee")
public class Employee {
  @Id
  @SequenceGenerator(name="Employee_SEQ",sequenceName="Employee_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Employee_SEQ")
  @Column(name="Employee_id",unique = true, nullable = true)
  private @NonNull Long id;
  private @NonNull String name;

  @OneToMany(fetch = FetchType.EAGER)
  private Collection<ClubEvent> Event;

}