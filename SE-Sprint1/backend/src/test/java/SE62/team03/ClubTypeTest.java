package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import SE62.team03.Entity.ClubType;
import SE62.team03.Repository.ClubTypeRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class ClubTypeTest {

    private Validator validator;

    @Autowired
    ClubTypeRepository ClubTypeRepository;
    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testClubTypeCanSave() {
        ClubType clubType = new ClubType();
        clubType.setName("ClubType Name");
        clubType.setId(1L);
        clubType = ClubTypeRepository.saveAndFlush(clubType);

        Optional<ClubType> result = ClubTypeRepository.findById(clubType.getId());

        assertEquals(1L, result.get().getId());
        assertEquals("ClubType Name", result.get().getName());
    }

    @Test
    void testClubTypeNameMustNotBeNull() {
        ClubType clubType = new ClubType();
        clubType.setName(null);
        clubType.setId(1L);
        Set<ConstraintViolation<ClubType>> result = validator.validate(clubType);

        assertEquals(1, result.size());

        ConstraintViolation<ClubType> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void testClubTypeIdMustNotBeNull() {
        ClubType clubType = new ClubType();
        clubType.setName("ClubType Name");
        clubType.setId(null);

        Set<ConstraintViolation<ClubType>> result = validator.validate(clubType);

        assertEquals(1, result.size());

        ConstraintViolation<ClubType> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }
   
    @Test
    void testClubTypeNameMustNotLessThan5() {
        ClubType clubType = new ClubType();
        clubType.setName("Club");
        clubType.setId(1L);
        Set<ConstraintViolation<ClubType>> result = validator.validate(clubType);

        assertEquals(1, result.size());

        ConstraintViolation<ClubType> v = result.iterator().next();
        assertEquals("size must be between 5 and 15", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void testClubTypeNameMustNotMoreThan15() {
        ClubType clubType = new ClubType();
        clubType.setName("Club Type Number 1");
        clubType.setId(1L);
        Set<ConstraintViolation<ClubType>> result = validator.validate(clubType);

        assertEquals(1, result.size());

        ConstraintViolation<ClubType> v = result.iterator().next();
        assertEquals("size must be between 5 and 15", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }
}
