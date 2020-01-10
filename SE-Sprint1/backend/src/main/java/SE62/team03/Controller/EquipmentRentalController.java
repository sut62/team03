package SE62.team03.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import SE62.team03.Repository.*;
import SE62.team03.Entity.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class EquipmentRentalController {
    @Autowired
    private EquipmentRentalRepository equipmentRentalRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private ClubsRepository clubsRepository;
    @Autowired
    private OfficerRepository officerRepository;

    EquipmentRentalController(EquipmentRentalRepository equipmentRentalRepository,
            EquipmentRepository equipmentRepository, ClubsRepository clubsRepository,
            OfficerRepository officerRepository) {
    }

    @GetMapping("/equipmentrentals")
    public Collection<EquipmentRental> getEquipmentrentals() {
        return equipmentRentalRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/equipmentrental/{club_id}/{officer_id}/{equipment_id}/{note}/{renter_name}")
    public EquipmentRental newEquipmentRental(
        @PathVariable long club_id, 
        @PathVariable long officer_id,
        @PathVariable long equipment_id, 
        @PathVariable String note, 
        @PathVariable String renter_name) {

        EquipmentRental equipmentRental = new EquipmentRental();
        Clubs clubs = clubsRepository.findById(club_id);
        Officer officer = officerRepository.findById(officer_id);
        Equipment equipment = equipmentRepository.findById(equipment_id);
        
        if (note.equals("null")) {
            equipmentRental.setNote("-");
        } else {
            equipmentRental.setNote(note);
        }
        equipmentRental.setClubs(clubs);
        equipmentRental.setRenter_name(renter_name);
        equipmentRental.setEquipment(equipment);
        equipmentRental.setOfficer(officer);
        equipmentRental.setEquipmentRental_date(new Date());
        return equipmentRentalRepository.save(equipmentRental); // บันทึก Objcet ชื่อ RightAssingment

    }
}
