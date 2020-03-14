package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import SE62.team03.Entity.*;
import SE62.team03.Repository.*;

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
    private ClubType ClubType;
    private Clubs Clubs;
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
    }

    @Test
    void testSaveClubMember() {
        Clubmember clubmember = new Clubmember();
        clubmember.setName("Club Member1");
        clubmember.setId(1L);
        clubmember.setClubs(Clubs);
        clubmember.setBranch(Branch);
        clubmember.setYear(Year);
        clubmember = clubmemberRepository.saveAndFlush(clubmember);
        Optional<Clubmember> result = clubmemberRepository.findById(clubmember.getId());

        assertEquals(1L, result.get().getId());
        assertEquals("Club Member1", result.get().getName());
        assertEquals(Clubs, result.get().getClubs());
        assertEquals(Year, result.get().getYear());
        assertEquals(Branch, result.get().getBranch());
    }

    @Test
    void testClubmemberNameMustNotBeNull() {
        Clubmember clubmember = new Clubmember();
        clubmember.setName(null);
        clubmember.setId(1L);
        clubmember.setClubs(Clubs);
        clubmember.setBranch(Branch);
        clubmember.setYear(Year);
        Set<ConstraintViolation<Clubmember>> result = validator.validate(clubmember);

        assertEquals(1, result.size());

        ConstraintViolation<Clubmember> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ClubmemberName", v.getPropertyPath().toString());
    }

    @Test
    void testClubmemberIdMustNotBeNull() {
        Clubmember clubmember = new Clubmember();
        clubmember.setName("Club Member1");
        clubmember.setId(null);
        clubmember.setClubs(Clubs);
        clubmember.setBranch(Branch);
        clubmember.setYear(Year);

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
        clubmember.setClubs(Clubs);
        clubmember.setBranch(Branch);
        clubmember.setYear(Year);
        Set<ConstraintViolation<Clubmember>> result = validator.validate(clubmember);

        assertEquals(1, result.size());

        ConstraintViolation<Clubmember> v = result.iterator().next();
        assertEquals("must match \"^[0-9A-Za-zก-์\\s]+$\"", v.getMessage());
        assertEquals("ClubmemberName", v.getPropertyPath().toString());
    }

    @Test
    void testClubmemberNameNotLessThan3() {
        Clubmember clubmember = new Clubmember();
        clubmember.setName("Me");
        clubmember.setId(1L);
        clubmember.setClubs(Clubs);
        clubmember.setBranch(Branch);
        clubmember.setYear(Year);
        Set<ConstraintViolation<Clubmember>> result = validator.validate(clubmember);

        assertEquals(1, result.size());

        ConstraintViolation<Clubmember> v = result.iterator().next();
        assertEquals("size must be between 3 and 30", v.getMessage());
        assertEquals("ClubmemberName", v.getPropertyPath().toString());
    }

    @Test
    void testClubmemberNameNotMoreThan30() {
        Clubmember clubmember = new Clubmember();
        clubmember.setName("Member name must not more than 30");
        clubmember.setId(1L);
        clubmember.setClubs(Clubs);
        clubmember.setBranch(Branch);
        clubmember.setYear(Year);
        Set<ConstraintViolation<Clubmember>> result = validator.validate(clubmember);

        assertEquals(1, result.size());

        ConstraintViolation<Clubmember> v = result.iterator().next();
        assertEquals("size must be between 3 and 30", v.getMessage());
        assertEquals("ClubmemberName", v.getPropertyPath().toString());
    }

    @Test
    void testClubsMustNotBeNull() {
        Clubmember clubmember = new Clubmember();
        clubmember.setName("Member name");
        clubmember.setId(1L);
        clubmember.setClubs(null);
        clubmember.setBranch(Branch);
        clubmember.setYear(Year);
        Set<ConstraintViolation<Clubmember>> result = validator.validate(clubmember);

        assertEquals(1, result.size());

        ConstraintViolation<Clubmember> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Clubs", v.getPropertyPath().toString());
    }

    @Test
    void testBranchMustNotBeNull() {
        Clubmember clubmember = new Clubmember();
        clubmember.setName("Member name");
        clubmember.setId(1L);
        clubmember.setClubs(Clubs);
        clubmember.setBranch(null);
        clubmember.setYear(Year);
        Set<ConstraintViolation<Clubmember>> result = validator.validate(clubmember);

        assertEquals(1, result.size());

        ConstraintViolation<Clubmember> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Branch", v.getPropertyPath().toString());
    }

    @Test
    void testMustYearNotBeNull() {
        Clubmember clubmember = new Clubmember();
        clubmember.setName("Member name");
        clubmember.setId(1L);
        clubmember.setClubs(Clubs);
        clubmember.setBranch(Branch);
        clubmember.setYear(null);
        Set<ConstraintViolation<Clubmember>> result = validator.validate(clubmember);

        assertEquals(1, result.size());

        ConstraintViolation<Clubmember> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Year", v.getPropertyPath().toString());
    }
}