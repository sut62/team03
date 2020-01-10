package SE62.team03.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import SE62.team03.Repository.*;
import SE62.team03.Entity.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class EquipmentController {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public EquipmentController(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    @GetMapping("/equipments")
    public Collection<Equipment> getEquipments() {
        return equipmentRepository.findAll().stream().collect(Collectors.toList());
    }

}