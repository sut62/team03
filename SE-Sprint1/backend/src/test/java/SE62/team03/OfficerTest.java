package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import SE62.team03.Entity.Officer;
import SE62.team03.Repository.OfficerRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class OfficerTest {

    private Validator validator;

    @Autowired
    OfficerRepository OfficerRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testOfficerSave() {
        Officer officer = new Officer();
        officer.setName("Officer name");
        officer.setOfficerStatus(1L);
        officer.setPassword("1234");
        officer.setUsername("55555");
        officer.setId(1L);
        officer = OfficerRepository.saveAndFlush(officer);

        Optional<Officer> result = OfficerRepository.findById(officer.getId());
        assertEquals(1L, result.get().getId());
        assertEquals("Officer name", result.get().getName());
        assertEquals(1L, result.get().getOfficerStatus());
        assertEquals("1234", result.get().getPassword());
        assertEquals("55555", result.get().getUsername());
    }

    @Test
    void testOfficerNameMustNotBeNull() {
        Officer officer = new Officer();
        officer.setName(null);
        officer.setOfficerStatus(1L);
        officer.setPassword("1234");
        officer.setUsername("55555");
        officer.setId(1L);
        Set<ConstraintViolation<Officer>> result = validator.validate(officer);

        assertEquals(1, result.size());

        ConstraintViolation<Officer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void testOfficerIdMustNotBeNull() {
        Officer officer = new Officer();
        officer.setName("Officer name");
        officer.setOfficerStatus(1L);
        officer.setPassword("1234");
        officer.setUsername("55555");
        officer.setId(null);

        Set<ConstraintViolation<Officer>> result = validator.validate(officer);

        assertEquals(1, result.size());

        ConstraintViolation<Officer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }

    @Test
    void testOfficerStatusMustNotBeNull() {
        Officer officer = new Officer();
        officer.setName("Officer name");
        officer.setOfficerStatus(null);
        officer.setPassword("1234");
        officer.setUsername("55555");
        officer.setId(1L);

        Set<ConstraintViolation<Officer>> result = validator.validate(officer);

        assertEquals(1, result.size());

        ConstraintViolation<Officer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("OfficerStatus", v.getPropertyPath().toString());
    }

    @Test
    void testPasswordMustNotBeNull() {
        Officer officer = new Officer();
        officer.setName("Officer name");
        officer.setOfficerStatus(1L);
        officer.setPassword(null);
        officer.setUsername("55555");
        officer.setId(1L);

        Set<ConstraintViolation<Officer>> result = validator.validate(officer);

        assertEquals(1, result.size());

        ConstraintViolation<Officer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("password", v.getPropertyPath().toString());
    }

    @Test
    void testUsernameMustNotBeNull() {
        Officer officer = new Officer();
        officer.setName("Officer name");
        officer.setOfficerStatus(1L);
        officer.setPassword("12345");
        officer.setUsername(null);
        officer.setId(1L);

        Set<ConstraintViolation<Officer>> result = validator.validate(officer);

        assertEquals(1, result.size());

        ConstraintViolation<Officer> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("username", v.getPropertyPath().toString());
    }

    @Test
    void B5823475_testUsernameMustBeDigit() {
        Officer officer = new Officer();
        officer.setName("Officer name");
        officer.setOfficerStatus(1L);
        officer.setPassword("1234");
        officer.setUsername("B1234");
        officer.setId(1L);

        Set<ConstraintViolation<Officer>> result = validator.validate(officer);

        assertEquals(1, result.size());

        ConstraintViolation<Officer> v = result.iterator().next();
        assertEquals("must match \"\\d{5}\"", v.getMessage());
        assertEquals("username", v.getPropertyPath().toString());
    }

    @Test
    void B5823475_testUsernameMustNotLessThan4() {
        Officer officer = new Officer();
        officer.setName("Officer name");
        officer.setOfficerStatus(1L);
        officer.setPassword("1234");
        officer.setUsername("1234");
        officer.setId(1L);

        Set<ConstraintViolation<Officer>> result = validator.validate(officer);

        assertEquals(1, result.size());

        ConstraintViolation<Officer> v = result.iterator().next();
        assertEquals("must match \"\\d{5}\"", v.getMessage());
        assertEquals("username", v.getPropertyPath().toString());
    }

    @Test
    void B5823475_testUsernameMustNotMoreThan5() {
        Officer officer = new Officer();
        officer.setName("Officer name");
        officer.setOfficerStatus(1L);
        officer.setPassword("1234");
        officer.setUsername("123456");
        officer.setId(1L);

        Set<ConstraintViolation<Officer>> result = validator.validate(officer);

        assertEquals(1, result.size());

        ConstraintViolation<Officer> v = result.iterator().next();
        assertEquals("must match \"\\d{5}\"", v.getMessage());
        assertEquals("username", v.getPropertyPath().toString());
    }
}
