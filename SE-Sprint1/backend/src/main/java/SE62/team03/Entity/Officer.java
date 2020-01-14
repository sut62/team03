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
  @Column(name="Officer_ID",unique = true, nullable = true)
  private @NonNull int id;
  private @NonNull String name;
  private @NonNull String username;
  private @NonNull String password;
  private @NonNull int OfficerStatus; //ใช้เช็คตอน login

  @OneToMany(fetch = FetchType.EAGER)
  private Collection<ClubEvent> Event;

  public void setName(String name) {
    this.name = name;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setOfficerStatus(int officerStatus) {
    this.OfficerStatus = officerStatus;
  }

}