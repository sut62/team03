package SE62.team03.Controller;
import SE62.team03.Repository.*;
import SE62.team03.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class OfficerController {
    @Autowired
    private final OfficerRepository OfficerRepository;
    public OfficerController(OfficerRepository OfficerRepository) {
        this.OfficerRepository = OfficerRepository;
    }
    @GetMapping("/Officer")
    public Collection<Officer> Officer() {
        return OfficerRepository.findAll().stream().collect(Collectors.toList());
    }
}