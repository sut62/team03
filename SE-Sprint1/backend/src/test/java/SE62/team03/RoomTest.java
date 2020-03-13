package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import SE62.team03.Entity.Room;
import SE62.team03.Repository.RoomRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class RoomTest {

    private Validator validator;

    @Autowired
    RoomRepository roomRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testSaveRoom() {
        Room room = new Room();
        room.setName("RoomName");
        room.setId(1L);
        room = roomRepository.saveAndFlush(room);

        Optional<Room> result = roomRepository.findById(room.getId());

        assertEquals(1L, result.get().getId());
        assertEquals("RoomName", result.get().getName());
    }

    @Test
    void tesRoomNameMustNotBeNull() {
        Room room = new Room();
        room.setName(null);
        room.setId(1L);
        Set<ConstraintViolation<Room>> result = validator.validate(room);

        assertEquals(1, result.size());

        ConstraintViolation<Room> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void testRoomIdMustNotBeNull() {
        Room room = new Room();
        room.setName("RoomName");
        room.setId(null);

        Set<ConstraintViolation<Room>> result = validator.validate(room);

        assertEquals(1, result.size());

        ConstraintViolation<Room> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }

    @Test
    void tesRoomNameMustEnterOnlyNumberAndAlpha() {
        Room room = new Room();
        room.setName("Room1@");
        room.setId(1L);
        Set<ConstraintViolation<Room>> result = validator.validate(room);

        assertEquals(1, result.size());

        ConstraintViolation<Room> v = result.iterator().next();
        assertEquals("must match \"^[A-Za-z0-9]+$\"", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }
}
