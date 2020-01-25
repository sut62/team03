package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import SE62.team03.Entity.Budget;
import SE62.team03.Repository.BudgetRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class BudgetTest {

    private Validator validator;

    @Autowired
    BudgetRepository BudgetRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5815234_testSaveBudget() {
        Budget budget = new Budget();
        budget.setBudgetId(1L);
        budget.setAmount(2000);
        budget = BudgetRepository.saveAndFlush(budget);

        Optional<Budget> found = BudgetRepository.findById(budget.getBudgetId());
        
        assertEquals(1L, found.get().getBudgetId());
        assertEquals(2000, found.get().getAmount());
    }

    @Test
    void B5815234_testBudgetIdMustNotBeNull() {
        Budget budget = new Budget();
        budget.setAmount(2000);
        budget.setBudgetId(null);
        Set<ConstraintViolation<Budget>> result = validator.validate(budget);
        
        assertEquals(1, result.size());
        
        ConstraintViolation<Budget> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("budgetId", v.getPropertyPath().toString());
    }

    @Test
    void B5815234_testAmountMustNotBeNull() {
        Budget budget = new Budget();
        budget.setAmount(null);
        budget.setBudgetId(1L);
        Set<ConstraintViolation<Budget>> result = validator.validate(budget);
        
        assertEquals(1, result.size());

        ConstraintViolation<Budget> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }

    @Test
    void B5815234_testAmountMustNotBeLessThan0() {
        Budget budget = new Budget();
        budget.setAmount(-1);
        budget.setBudgetId(1L);
        Set<ConstraintViolation<Budget>> result = validator.validate(budget);
        
        assertEquals(1, result.size());

        ConstraintViolation<Budget> v = result.iterator().next();
        assertEquals("must be greater than or equal to 0", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }

    @Test
    void B5815234_testAmountMustNotBeMoreThan100000() {
        Budget budget = new Budget();
        budget.setAmount(100001);
        budget.setBudgetId(1L);
        Set<ConstraintViolation<Budget>> result = validator.validate(budget);
        
        assertEquals(1, result.size());

        ConstraintViolation<Budget> v = result.iterator().next();
        assertEquals("must be less than or equal to 100000", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }
}
