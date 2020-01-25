package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import SE62.team03.Entity.Branch;
import SE62.team03.Repository.BranchRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class BranchTest {

    private Validator validator;

    @Autowired
    BranchRepository branchRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testSaveBranch() {
        Branch branch = new Branch();
        branch.setId(1L);
        branch.setName("Branch");
        branch = branchRepository.saveAndFlush(branch);

        Optional<Branch> found = branchRepository.findById(branch.getId());

        assertEquals(1L, found.get().getId());
        assertEquals("Branch", found.get().getName());
    }

    @Test
    void tesBranchNameMustNotBeNull() {
        Branch branch = new Branch();
        branch.setName(null);
        branch.setId(1L);
        Set<ConstraintViolation<Branch>> result = validator.validate(branch);

        assertEquals(1, result.size());

        ConstraintViolation<Branch> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("name", v.getPropertyPath().toString());
    }

    @Test
    void testBranchIdMustNotBeNull() {
        Branch branch = new Branch();
        branch.setName("Branch name");
        branch.setId(null);

        Set<ConstraintViolation<Branch>> result = validator.validate(branch);

        assertEquals(1, result.size());

        ConstraintViolation<Branch> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }
}
