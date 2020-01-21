package SE62.team03.Entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

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
  private @NotNull String ClubEventName;
  private @NotNull String ClubEventDate;
  private @NotNull String ClubHost;

  private 
  //@Size (min = 1,max = 3)
  //@Pattern(regexp = "\\d{5-100}")
  @NotNull Long ClubEventPepleAmount;
  

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Clubs.class)
  @JoinColumn(name = "Clubs_ID", insertable = true)
  @Getter @Setter private Clubs clubs;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Officer.class)
  @JoinColumn(name = "Officer_ID", insertable = true)
  @Getter @Setter private Officer officer;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Location.class)
  @JoinColumn(name = "Location_ID", insertable = true)
  @Getter @Setter private Location location;

  public Long getId() {
    return clubEventID;
}



}