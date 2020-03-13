package SE62.team03.Entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.*;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "BOOKING")
public class Booking {

    @Id
    @SequenceGenerator(name = "booking_seq", sequenceName = "booking_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
    @Column(name = "BOOKING_ID", unique = true)
    private @NotNull Long id;

    @Column(name = "BOOKING_DATE")
    @Temporal(TemporalType.DATE)
    private @NotNull Date bookingDate;//

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Sitestaff.class)
    @JoinColumn(name = "SITESTAFF_ID", insertable = true)
    private Sitestaff sitestaff;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Clubmember.class)
    @JoinColumn(name = "CLUBMEMBER_ID", insertable = true)
    private Clubmember clubmember;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Clubs.class)
    @JoinColumn(name = "CLUB_ID", insertable = true)
    private Clubs bookingClub;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Room.class)
    @JoinColumn(name = "ROOM_ID", insertable = true)
    private Room type;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getBookingDate() {//
//        return bookingDate;
//    }
//
//    public void setBookingDate(Date bookingDate) {//
//        this.bookingDate = bookingDate;
//    }
//
//    public Sitestaff getSitestaff() {
//        return sitestaff;
//    }
//
//    public void setSitestaff(Sitestaff sitestaff) {
//        this.sitestaff = sitestaff;
//    }
//
//    public Clubmember getClubmember() {
//        return clubmember;
//    }
//
//    public void setClubmember(Clubmember clubmember) {
//        this.clubmember = clubmember;
//    }
//
//    public Clubs getBookingClub() {
//        return bookingClub;
//    }
//
//    public void setBookingClub(Clubs bookingClub) {
//        this.bookingClub = bookingClub;
//    }
//
//    public Room getType() {
//        return type;
//    }
//
//    public void setType(Room type) {
//        this.type = type;
//    }

}