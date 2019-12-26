package SE62.team03.Entity;
import lombok.*;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name="ClubEvent")
public class ClubEvent {
  @Id
  @SequenceGenerator(name="ClubEvent_SEQ",sequenceName="ClubEvent_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ClubEvent_SEQ")
  @Column(name="ClubEvent_id",unique = true, nullable = true)
  private @NonNull Long clubEventID;
  private @NonNull String clubEventName;
  private @NonNull String clubEventLocationName;
  private @NonNull String clubEventDate;
  private @NonNull String clubEventHost;
  private @NonNull String clubEventOfficer;
  private @NonNull String clubEventLocationType;
  private @NonNull String clubEventClub;
  

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Clubs.class)
  @JoinColumn(name = "Clubs_ID", insertable = true)
  private Clubs clubs;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Employee.class)
  @JoinColumn(name = "Employee_ID", insertable = true)
  private Employee employee;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Location.class)
  @JoinColumn(name = "Location_ID", insertable = true)
  private Location location;


}