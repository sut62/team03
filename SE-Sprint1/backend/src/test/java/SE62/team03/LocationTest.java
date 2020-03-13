package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import SE62.team03.Entity.Location;
import SE62.team03.Repository.LocationRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class LocationTest {

    private Validator validator;

    @Autowired
    LocationRepository locationRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5823475_testCreateLocation() {
        Location location = new Location();
        location.setName("อาคารสุรเริงชัย");
        location.setId(1L);
        location = locationRepository.saveAndFlush(location);

        Optional<Location> result = locationRepository.findById(location.getId());

        assertEquals(1L, result.get().getId());
        assertEquals("อาคารสุรเริงชัย", result.get().getName());
    }

    @Test
    void B5823475_testLocationNameMustNotBeNull() {
        Location location = new Location();
        location.setName(null);
        location.setId(1L);
        Set<ConstraintViolation<Location>> result = validator.validate(location);

        assertEquals(1, result.size());

        ConstraintViolation<Location> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void B5823475_testLocationIdMustNotBeNull() {
        Location location = new Location();
        location.setName("location");
        location.setId(null);

        Set<ConstraintViolation<Location>> result = validator.validate(location);

        assertEquals(1, result.size());

        ConstraintViolation<Location> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }

    @Test
    void B5823475_testLocationNameNotLessThan4() {
        Location location = new Location();
        location.setName("ABC");
        location.setId(1L);
        Set<ConstraintViolation<Location>> result = validator.validate(location);
        assertEquals(1, result.size());

        ConstraintViolation<Location> v = result.iterator().next();
        assertEquals("size must be between 4 and 30", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void B5823475_testLocationNameNotMoreThan30() {
        Location location = new Location();
        location.setName("อาคารสุรเริงชัย อาคารบริหาร อาคารเรัียนรวมสอง");
        location.setId(1L);
        Set<ConstraintViolation<Location>> result = validator.validate(location);
        assertEquals(1, result.size());
        
        ConstraintViolation<Location> v = result.iterator().next();
        assertEquals("size must be between 4 and 30", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }
}
