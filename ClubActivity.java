package Club.Activity.Entity;
import lombok.*;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="ClubActivity")
public class ClubActivity {
  @Id
  @SequenceGenerator(name="ClubActivity_SEQ",sequenceName="ClubActivity_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ClubActivity_SEQ")
  @Column(name="ClubActivity_id",unique = true, nullable = true)
  private @NonNull Long id;
  private @NonNull String name;

}