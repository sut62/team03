package SE62.team03.Entity;
import lombok.*;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@NoArgsConstructor
@Table(name="ClubEvent")
public class ClubEvent {
  @Id
  @SequenceGenerator(name="ClubEvent_SEQ",sequenceName="ClubEvent_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ClubEvent_SEQ")
  @Column(name="ClubEvent_ID",unique = true, nullable = true)
  @Getter @Setter private @NonNull Long clubEventID;

  @Getter @Setter private @NonNull String ClubEventName;
  @Getter @Setter private @NonNull String ClubEventDate;
  @Getter @Setter private @NonNull String ClubHost;
  @Getter @Setter private @NonNull long ClubEventPepleAmount;
  

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Clubs.class)
  @JoinColumn(name = "Clubs_ID", insertable = true)
  @Getter @Setter private Clubs clubs;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Officer.class)
  @JoinColumn(name = "Officer_ID", insertable = true)
  @Getter @Setter private Officer officer;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Location.class)
  @JoinColumn(name = "Location_ID", insertable = true)
  @Getter @Setter private Location location;

}