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
  @Column(name="ClubEvent_ID",unique = true, nullable = true)
  private @NonNull Long clubEventID;

  private @NonNull String ClubEventName;
  private @NonNull String ClubEventDate;
  private @NonNull String ClubHost;
  private @NonNull long ClubEventPepleAmount;
  

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Clubs.class)
  @JoinColumn(name = "Clubs_ID", insertable = true)
  private Clubs clubs;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Officer.class)
  @JoinColumn(name = "Officer_ID", insertable = true)
  private Officer officer;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Location.class)
  @JoinColumn(name = "Location_ID", insertable = true)
  private Location location;


}