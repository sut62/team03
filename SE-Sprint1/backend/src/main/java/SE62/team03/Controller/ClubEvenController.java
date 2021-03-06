package SE62.team03.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.format.annotation.DateTimeFormat;
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
import lombok.Getter;
import lombok.Setter;
import SE62.team03.Repository.*;
import SE62.team03.Entity.*;



import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ClubEvenController {
    @Autowired
    private final ClubEventRepository ClubEventRepository;
    @Autowired
    private ClubsRepository ClubsRepository;
    @Autowired
    private OfficerRepository OfficerRepository;
    @Autowired
    private LocationRepository LocationRepository;

    ClubEvenController(ClubEventRepository ClubEventRepository) {
        this.ClubEventRepository = ClubEventRepository;
    }

    @GetMapping("/ClubEvent")
    public List<ClubEvent> ClubEvent() {
        return ClubEventRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/ClubEvent/{ClubID}/{ClubEventName}/{LocationID}//{ClubEventDate}/{ClubEventPepleAmount}/{ClubHost}/{OfficerID}")
    public ClubEvent newClubEvent(
    @PathVariable long ClubID,
    @PathVariable String ClubEventName,
    @PathVariable long LocationID,
    @PathVariable("ClubEventDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date ClubEventDate,
    @PathVariable long ClubEventPepleAmount,
    @PathVariable String ClubHost,
    @PathVariable long OfficerID){


    ClubEvent newClubEvent = new ClubEvent();
    Clubs clubs = ClubsRepository.findById(ClubID);
    Location location = LocationRepository.findById(LocationID);
    Officer officer = OfficerRepository.findById(OfficerID);
    
    newClubEvent.setClubs(clubs);
    newClubEvent.setClubEventName(ClubEventName);
    newClubEvent.setLocation(location);
    newClubEvent.setClubEventDate(ClubEventDate);
    newClubEvent.setClubEventPepleAmount(ClubEventPepleAmount);
    newClubEvent.setClubHost(ClubHost);
    newClubEvent.setOfficer(officer);
    newClubEvent.setClubEventStatus("รออนุมัติ");
    
    return ClubEventRepository.save(newClubEvent); //บันทึก Objcet 
    
    }

    @PostMapping("/approveevent/{clubeventId}")
    public ClubEvent approveClubEvent(@PathVariable long clubeventId) {
        ClubEvent clubEvent = ClubEventRepository.findById(clubeventId);
        clubEvent.setClubEventStatus("อนุมัติ");
        return ClubEventRepository.save(clubEvent);
    }

    @DeleteMapping("/disapprove/{clubeventId}")
    public Boolean disApproveClubEvent(@PathVariable long clubeventId) {
        System.out.println("Doing");
        ClubEventRepository.deleteById(clubeventId);
        ClubEvent clubEvent = ClubEventRepository.findById(clubeventId);
        System.out.println(clubEvent);
        if(clubEvent != null) {
            return true;
        }
        return false;
    }
}