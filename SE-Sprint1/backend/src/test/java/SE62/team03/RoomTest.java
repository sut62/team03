package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import SE62.team03.Entity.Room;
import SE62.team03.Repository.RoomRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
        room.setName("Room name");
        room.setId(null);

        Set<ConstraintViolation<Room>> result = validator.validate(room);

        assertEquals(1, result.size());

        ConstraintViolation<Room> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }
}
