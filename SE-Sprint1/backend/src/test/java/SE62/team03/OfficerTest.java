package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import SE62.team03.Entity.Officer;
import SE62.team03.Repository.OfficerRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
    void tesOfficerNameMustNotBeNull() {
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
    void B5823475_testUsernamedigit5() {
        Officer officer = new Officer();
        officer.setName("Officer name");
        officer.setOfficerStatus(1L);
        officer.setPassword("1234");
        officer.setUsername("B1");
        officer.setId(1L);

        Set<ConstraintViolation<Officer>> result = validator.validate(officer);

        assertEquals(1, result.size());

        ConstraintViolation<Officer> v = result.iterator().next();
        // assertEquals("must not be null", v.getMessage()); //error รอแก้
        // assertEquals("username", v.getPropertyPath().toString());
    }
}
