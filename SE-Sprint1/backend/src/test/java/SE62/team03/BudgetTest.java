package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import SE62.team03.Entity.Budget;
import SE62.team03.Repository.BudgetRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
    void B5815234_testBudgetIdMustNotBeNull() {
        Budget budget = new Budget();
        budget.setAmount(123456);
        budget.setBudgetId(null);

        Set<ConstraintViolation<Budget>> result = validator.validate(budget);
        ConstraintViolation<Budget> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        // assertEquals("budgetId", v.getPropertyPath().toString()); รอแก้
    }
}
