package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import SE62.team03.Entity.Branch;
import SE62.team03.Entity.Budget;
import SE62.team03.Entity.ClubType;
import SE62.team03.Entity.Clubmember;
import SE62.team03.Entity.Clubs;
import SE62.team03.Entity.Officer;
import SE62.team03.Entity.Year;
import SE62.team03.Repository.BranchRepository;
import SE62.team03.Repository.BudgetRepository;
import SE62.team03.Repository.ClubTypeRepository;
import SE62.team03.Repository.ClubmemberRepository;
import SE62.team03.Repository.ClubsRepository;
import SE62.team03.Repository.OfficerRepository;
import SE62.team03.Repository.YearRepository;

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
    private ClubType ClubType;
    private Clubs Clubs;
    private Clubmember Clubmember;
    private Branch Branch;
    private Officer Officer;
    private Year Year;

    @Autowired
    ClubsRepository clubsRepository;

    @Autowired
    BudgetRepository BudgetRepository;

    @Autowired
    ClubTypeRepository clubTypeRepository;

    @Autowired
    YearRepository yearRepository;

    @Autowired
    OfficerRepository officerRepository;

    @Autowired
    BranchRepository branchRepository;

    @Autowired 
    ClubmemberRepository clubmemberRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        ClubType = new ClubType(); 
        ClubType.setName("Club Type");
        ClubType = clubTypeRepository.saveAndFlush(ClubType);

        Year = new Year();
        Year.setName("2019");
        Year = yearRepository.saveAndFlush(Year);

        Officer = new Officer();
        Officer.setName("Name");
        Officer.setOfficerStatus(1L);
        Officer.setPassword("1234");
        Officer.setUsername("12345");
        Officer = officerRepository.saveAndFlush(Officer);

        Branch = new Branch();
        Branch.setName("Name");
        Branch = branchRepository.saveAndFlush(Branch);

        Clubs = new Clubs();
        Clubs.setAdvisor("advisors");
        Clubs.setName("clubName");
        Clubs.setObjective("Objective");
        Clubs.setClubPresident("ClubPresident");
        Clubs.setBranch(Branch);
        Clubs.setYear(Year);
        Clubs.setClubType(ClubType);
        Clubs.setOfficer(Officer);
        Clubs = clubsRepository.saveAndFlush(Clubs);

        Clubmember = new Clubmember();
        Clubmember.setClubmemberName("name");
        Clubmember.setYear(Year);
        Clubmember.setBranch(Branch);
        Clubmember.setClubs(Clubs);
        Clubmember = clubmemberRepository.saveAndFlush(Clubmember);

    }

    @Test
    void B5815234_testSaveBudget() {
        Budget budget = new Budget();
        budget.setBudgetId(1L);
        budget.setAmount(2000);
        budget.setClubmember(Clubmember);
        budget.setClubType(ClubType);
        budget.setClubs(Clubs);
        budget = BudgetRepository.saveAndFlush(budget);

        Optional<Budget> found = BudgetRepository.findById(budget.getBudgetId());
        
        assertEquals(1L, found.get().getBudgetId());
        assertEquals(2000, found.get().getAmount());
        assertEquals(ClubType, found.get().getClubType());
        assertEquals(Clubs, found.get().getClubs());
        assertEquals(Clubmember, found.get().getClubmember());
    }

    @Test
    void B5815234_testBudgetIdMustNotBeNull() {
        Budget budget = new Budget();
        budget.setAmount(2000);
        budget.setBudgetId(null);
        budget.setClubmember(Clubmember);
        budget.setClubType(ClubType);
        budget.setClubs(Clubs);
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
        budget.setClubmember(Clubmember);
        budget.setClubType(ClubType);
        budget.setClubs(Clubs);
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
        budget.setClubmember(Clubmember);
        budget.setClubType(ClubType);
        budget.setClubs(Clubs);
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
        budget.setClubmember(Clubmember);
        budget.setClubType(ClubType);
        budget.setClubs(Clubs);
        Set<ConstraintViolation<Budget>> result = validator.validate(budget);
        
        assertEquals(1, result.size());

        ConstraintViolation<Budget> v = result.iterator().next();
        assertEquals("must be less than or equal to 100000", v.getMessage());
        assertEquals("amount", v.getPropertyPath().toString());
    }

    @Test
    void B5815234_testClubMemberMustNotBeNull() {
        Budget budget = new Budget();
        budget.setAmount(1000);
        budget.setBudgetId(1L);
        budget.setClubmember(null);
        budget.setClubType(ClubType);
        budget.setClubs(Clubs);
        Set<ConstraintViolation<Budget>> result = validator.validate(budget);
        
        assertEquals(1, result.size());

        ConstraintViolation<Budget> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("clubmember", v.getPropertyPath().toString());
    }

    @Test
    void B5815234_testClubTypeMustNotBeNull() {
        Budget budget = new Budget();
        budget.setAmount(1000);
        budget.setBudgetId(1L);
        budget.setClubmember(Clubmember);
        budget.setClubType(null);
        budget.setClubs(Clubs);
        Set<ConstraintViolation<Budget>> result = validator.validate(budget);
        
        assertEquals(1, result.size());

        ConstraintViolation<Budget> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ClubType", v.getPropertyPath().toString());
    }

    @Test
    void B5815234_testClubsMustNotBeNull() {
        Budget budget = new Budget();
        budget.setAmount(1000);
        budget.setBudgetId(1L);
        budget.setClubmember(Clubmember);
        budget.setClubType(ClubType);
        budget.setClubs(null);
        Set<ConstraintViolation<Budget>> result = validator.validate(budget);
        
        assertEquals(1, result.size());

        ConstraintViolation<Budget> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("clubs", v.getPropertyPath().toString());
    }
}
