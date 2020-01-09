package SE62.team03.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.net.URLDecoder;

import SE62.team03.Repository.*;
import SE62.team03.Entity.*;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class BookingController {
    @Autowired
    private final BookingRepository bookingRepository;
    @Autowired
    private ClubmemberRepository clubmemberRepository;
    @Autowired
    private ClubsRepository clubsRepository;
    @Autowired
    private SitestaffRepository sitestaffRepository;
    @Autowired
    private RoomRepository roomRepository;

    BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @GetMapping("/booking")
    public Collection<Booking> Booking() {
        return bookingRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/booking/{club_id}/{sitestaff_id}/{clubmember_id}/{booking}/{date}")
    public Booking newBooking(@PathVariable long club_id, @PathVariable long sitestaff_id,
            @PathVariable long clubmember_id, @PathVariable long booking,
            @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date)
             {
        // VideoRental newVideoRental = new VideoRental();
        Booking newBooking = new Booking();
        Clubs bookingClub = clubsRepository.findById(club_id);
        Clubmember clubmember = clubmemberRepository.findById(clubmember_id);
        Sitestaff sitestaff = sitestaffRepository.findById(sitestaff_id);
        Room type = roomRepository.findById(booking);
       
        newBooking.setSitestaff(sitestaff);
        newBooking.setClubmember(clubmember);
        newBooking.setBookingDate(date);
        newBooking.setBookingClub(bookingClub);
        newBooking.setType(type);

        return bookingRepository.save(newBooking); // บันทึก Objcet ชื่อ VideoRental

    }

    
}