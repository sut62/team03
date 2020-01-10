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
    void tesLocationNameMustNotBeNull() {
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
    void testLocationIdMustNotBeNull() {
        Location location = new Location();
        location.setName("location name");
        location.setId(null);

        Set<ConstraintViolation<Location>> result = validator.validate(location);

        assertEquals(1, result.size());

        ConstraintViolation<Location> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }
}
