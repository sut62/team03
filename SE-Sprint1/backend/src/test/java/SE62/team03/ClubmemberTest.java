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
    void tesBranchNameMustNotBeNull() {
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
}
