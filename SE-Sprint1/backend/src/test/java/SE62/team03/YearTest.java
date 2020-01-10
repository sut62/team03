package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import SE62.team03.Entity.Year;
import SE62.team03.Repository.YearRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class YearTest {

    private Validator validator;

    @Autowired
    YearRepository yearRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void tesYearNameMustNotBeNull() {
        Year year = new Year();
        year.setName(null);
        year.setId(1L);
        Set<ConstraintViolation<Year>> result = validator.validate(year);

        assertEquals(1, result.size());

        ConstraintViolation<Year> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void testYearIdMustNotBeNull() {
        Year year = new Year();
        year.setName("Year Name");
        year.setId(null);

        Set<ConstraintViolation<Year>> result = validator.validate(year);

        assertEquals(1, result.size());

        ConstraintViolation<Year> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }
}
