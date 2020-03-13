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
@Table(name="Clubs")
public class Clubs {
  @Id
  @SequenceGenerator(name="Clubs_SEQ",sequenceName="Clubs_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="Clubs_SEQ")
  @Column(name="Clubs_ID",unique = true, nullable = true)
  

  private @NotNull Long id;
  @Size(min = 5, max = 25)
  private @NotNull String ClubName;
  @Pattern(regexp = "^[A-Za-zก-์\\s]+$")
  private @NotNull String ClubPresident;
  private @NotNull String Objective;
  private @NotNull String advisors;

  @OneToMany(fetch = FetchType.EAGER)
  private Collection<ClubEvent> Event;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Branch.class)
    @JoinColumn(name = "Branch_ID", insertable = true)
    private Branch Branch;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Year.class)
    @JoinColumn(name = "Year_ID", insertable = true)
    private Year Year;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Officer.class)
    @JoinColumn(name = "Officer_ID", insertable = true)
    private Officer officer;
   
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = ClubType.class)
    @JoinColumn(name = "ClubType_ID", insertable = true)
    private ClubType ClubType;


  public void setName(String clubName) {
    this.ClubName = clubName;
  }

  public void setAdvisor(String advisors) {
    this.advisors = advisors;
  }

  public void setClubPresident(String ClubPresident) {
    this.ClubPresident = ClubPresident;
  }

  public void setObjective(String Objective) {
    this.Objective = Objective;
  }

}