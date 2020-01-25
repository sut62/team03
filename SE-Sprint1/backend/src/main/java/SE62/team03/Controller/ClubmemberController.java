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

import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ClubmemberController {
    @Autowired
    private final ClubmemberRepository ClubmemberRepository;
    @Autowired
     private BranchRepository BranchRepository;
    @Autowired
    private YearRepository YearRepository;
    @Autowired
    private ClubsRepository ClubsRepository;
 

    ClubmemberController(ClubmemberRepository ClubmemberRepository) {
        this.ClubmemberRepository = ClubmemberRepository;
    }

    @GetMapping("/Clubmember")
    public Collection<Clubmember> Clubmembers() {
        return ClubmemberRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/Clubmember/{Branch_id}/{Year_id}/{Club_id}/{ClubmemberName}")
    public Clubmember newClubmember(
        @PathVariable long Branch_id, 
        @PathVariable long Year_id,
        @PathVariable long Club_id,
        @PathVariable String ClubmemberName
        ) {

        Clubmember newClubmember = new Clubmember();
        Branch Branch = BranchRepository.findById(Branch_id);
        Year Year = YearRepository.findById(Year_id);
        Clubs Club = ClubsRepository.findById(Club_id);
      

        newClubmember.setBranch(Branch);
        newClubmember.setYear(Year);
        newClubmember.setClubs(Club);
        newClubmember.setClubmemberName(ClubmemberName);
        
        return ClubmemberRepository.save(newClubmember); // บันทึก Objcet ชื่อ Clubmember

    }
}
