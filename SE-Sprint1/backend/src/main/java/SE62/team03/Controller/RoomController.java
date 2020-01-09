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
public class RoomController {

    @Autowired
    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/room")
    public Collection<Room> PaymentTypes() {
        return roomRepository.findAll().stream().collect(Collectors.toList());
    }

}