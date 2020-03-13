package SE62.team03.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
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
public class ClubsController {
    @Autowired
    private final ClubsRepository ClubsRepository;
    @Autowired
    private BranchRepository BranchRepository;
    @Autowired
    private YearRepository YearRepository;
    @Autowired
    private OfficerRepository OfficerRepository;
    @Autowired
    private ClubTypeRepository ClubTypeRepository;


    public ClubsController(ClubsRepository ClubsRepository) {
        this.ClubsRepository = ClubsRepository;
    }


    @GetMapping("/Clubs")
    public Collection<Clubs> Clubs() {
        return ClubsRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/Clubs/{Branch_id}/{Year_id}/{Officer_id}/{ClubType_id}/{ClubName}/{ClubPresident}/{Objective}/{advisors}")
    public Clubs newClub(
        @PathVariable long Branch_id, 
        @PathVariable long Year_id,
        @PathVariable long Officer_id,
        @PathVariable long ClubType_id, 
        @PathVariable String ClubName,
        @PathVariable String ClubPresident,
        @PathVariable String Objective,
        @PathVariable String advisors
        ) {

        Clubs newClub = new Clubs();
        Branch Branch = BranchRepository.findById(Branch_id);
        Year Year = YearRepository.findById(Year_id);
        Officer officer = OfficerRepository.findById(Officer_id);
        ClubType ClubType = ClubTypeRepository.findById(ClubType_id);

        newClub.setBranch(Branch);
        newClub.setYear(Year);
        newClub.setOfficer(officer);
        newClub.setClubType(ClubType);
        newClub.setClubName(ClubName);
        newClub.setClubPresident(ClubPresident);
        newClub.setObjective(Objective);
        newClub.setAdvisor(advisors);
        
        return ClubsRepository.save(newClub); // บันทึก Objcet ชื่อ Club

    }
}