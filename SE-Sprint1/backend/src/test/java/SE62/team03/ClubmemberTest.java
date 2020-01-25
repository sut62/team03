package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import SE62.team03.Entity.Clubmember;
import SE62.team03.Repository.ClubmemberRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class ClubmemberTest {

    private Validator validator;

    @Autowired
    ClubmemberRepository clubmemberRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testSaveClubMember() {
        Clubmember clubmember = new Clubmember();
        clubmember.setName("Club Member1");
        clubmember.setId(1L);
        clubmember = clubmemberRepository.saveAndFlush(clubmember);
        Optional<Clubmember> result = clubmemberRepository.findById(clubmember.getId());

        assertEquals(1L, result.get().getId());
        assertEquals("Club Member1", result.get().getName());
    }

    @Test
    void testClubmemberNameMustNotBeNull() {
        Clubmember clubmember = new Clubmember();
        clubmember.setName(null);
        clubmember.setId(1L);
        Set<ConstraintViolation<Clubmember>> result = validator.validate(clubmember);

        assertEquals(1, result.size());

        ConstraintViolation<Clubmember> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void testClubmemberIdMustNotBeNull() {
        Clubmember clubmember = new Clubmember();
        clubmember.setName("Club Member1");
        clubmember.setId(null);

        Set<ConstraintViolation<Clubmember>> result = validator.validate(clubmember);

        assertEquals(1, result.size());

        ConstraintViolation<Clubmember> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }

    @Test
    void testClubmemberNameNotHaveSpacialCharactor() {
        Clubmember clubmember = new Clubmember();
        clubmember.setName("Menber name @");
        clubmember.setId(1L);
        Set<ConstraintViolation<Clubmember>> result = validator.validate(clubmember);

        assertEquals(1, result.size());

        ConstraintViolation<Clubmember> v = result.iterator().next();
        assertEquals("must match \"^[0-9A-Za-zก-์\\s]+$\"", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void testClubmemberNameNotLessThan3() {
        Clubmember clubmember = new Clubmember();
        clubmember.setName("Me");
        clubmember.setId(1L);
        Set<ConstraintViolation<Clubmember>> result = validator.validate(clubmember);

        assertEquals(1, result.size());

        ConstraintViolation<Clubmember> v = result.iterator().next();
        assertEquals("size must be between 3 and 30", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void testClubmemberNameNotMoreThan30() {
        Clubmember clubmember = new Clubmember();
        clubmember.setName("Member name must not more than 30");
        clubmember.setId(1L);
        Set<ConstraintViolation<Clubmember>> result = validator.validate(clubmember);

        assertEquals(1, result.size());

        ConstraintViolation<Clubmember> v = result.iterator().next();
        assertEquals("size must be between 3 and 30", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }
}
