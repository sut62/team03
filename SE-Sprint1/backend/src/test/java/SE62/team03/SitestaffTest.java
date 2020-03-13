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
    SitestaffRepository SitestaffRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testSaveSitestaff() {
        Sitestaff sitestaff = new Sitestaff();
        sitestaff.setName("Sitestaff Name");
        sitestaff.setId(1L);
        sitestaff = SitestaffRepository.saveAndFlush(sitestaff);

        Optional<Sitestaff> result = SitestaffRepository.findById(sitestaff.getId());

        assertEquals(1L, result.get().getId());
        assertEquals("Sitestaff Name", result.get().getName());
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
        sitestaff.setName("Sitestaff Name");
        sitestaff.setId(null);

        Set<ConstraintViolation<Sitestaff>> result = validator.validate(sitestaff);

        assertEquals(1, result.size());

        ConstraintViolation<Sitestaff> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }

    @Test
    void tesSitestaffNameMustNotLessThan5() {
        Sitestaff Sitestaff = new Sitestaff();
        Sitestaff.setName("staf");
        Sitestaff.setId(1L);
        Set<ConstraintViolation<Sitestaff>> result = validator.validate(Sitestaff);

        assertEquals(1, result.size());

        ConstraintViolation<Sitestaff> v = result.iterator().next();
        assertEquals("size must be between 5 and 15", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void tesSitestaffNameMustNotMoreThan15() {
        Sitestaff Sitestaff = new Sitestaff();
        Sitestaff.setName("site staff should not more than 15");
        Sitestaff.setId(1L);
        Set<ConstraintViolation<Sitestaff>> result = validator.validate(Sitestaff);

        assertEquals(1, result.size());

        ConstraintViolation<Sitestaff> v = result.iterator().next();
        assertEquals("size must be between 5 and 15", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }
}
