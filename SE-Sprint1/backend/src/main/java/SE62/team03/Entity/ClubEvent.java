package SE62.team03.Entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@Table(name="ClubEvent")
public class ClubEvent {
  @Id
  @SequenceGenerator(name="ClubEvent_SEQ",sequenceName="ClubEvent_SEQ")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ClubEvent_SEQ")
  @Column(name="ClubEvent_ID",unique = true, nullable = true)

  private @NotNull Long clubEventID;
  private @NotNull String clubEventName;
  private @NotNull String clubEventDate;
  private @NotNull String clubHost;
  private @NotNull Long clubEventPepleAmount;
  String clubEventStatus;
  

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Clubs.class)
  @JoinColumn(name = "Clubs_ID", insertable = true)
  private Clubs clubs;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Officer.class)
  @JoinColumn(name = "Officer_ID", insertable = true)
  private Officer officer;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Location.class)
  @JoinColumn(name = "Location_ID", insertable = true)
  private Location location;

  public Officer getOfficer() {
    return this.officer;
}

public void setOfficer(Officer officer) {
    this.officer = officer;

}

public void setClubs(Clubs clubs) {
  this.clubs = clubs;
}
public Clubs getClubs() {
  return clubs;
}

public void setClubsEventStatus(String clubEventStatus) {
  this.clubEventStatus = clubEventStatus;
}
public String getClubEventStatus() {
  return clubEventStatus;
}

public void setClubEventName(String clubEventName) {
  this.clubEventName = clubEventName;
}
public String getClubEventName() {
  return clubEventName;
}







}