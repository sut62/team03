package SE62.team03.Entity;
import lombok.*;
import javax.persistence.*;
import java.util.*;
import javax.validation.constraints.NotNull;
@Data
@Entity
@NoArgsConstructor
@Table(name="Officer")
public class Officer {
  @Id
  @SequenceGenerator(name="Officer_SEQ",sequenceName="Officer_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Officer_SEQ")
  @Column(name="Officer_ID",unique = true, nullable = true)
  private @NotNull Long id;
  private @NotNull String name;
  private @NotNull String username;
  private @NotNull String password;
  private @NotNull Long OfficerStatus; //ใช้เช็คตอน login

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

  public void setOfficerStatus(Long OfficerStatus) {
    this.OfficerStatus = OfficerStatus;
  }

public void setId(Long id) {
  this.id = id;
}

}