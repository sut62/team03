package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import SE62.team03.Entity.Sitestaff;
import SE62.team03.Repository.SitestaffRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class SitestaffTest {

    private Validator validator;

    @Autowired
    SitestaffRepository sitestaffRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void tesSitestaffNameMustNotBeNull() {
        Sitestaff sitestaff = new Sitestaff();
        sitestaff.setName(null);
        sitestaff.setId(1L);
        Set<ConstraintViolation<Sitestaff>> result = validator.validate(sitestaff);

        assertEquals(1, result.size());

        ConstraintViolation<Sitestaff> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void testSitestaffIdMustNotBeNull() {
        Sitestaff sitestaff = new Sitestaff();
        sitestaff.setName("Sitestaff name");
        sitestaff.setId(null);

        Set<ConstraintViolation<Sitestaff>> result = validator.validate(sitestaff);

        assertEquals(1, result.size());

        ConstraintViolation<Sitestaff> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }
}
