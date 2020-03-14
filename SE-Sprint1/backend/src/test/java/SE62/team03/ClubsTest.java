package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import SE62.team03.Entity.*;
import SE62.team03.Repository.*;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
public class ClubsTest {

    private Validator validator;
    private ClubType ClubType;
    private Branch Branch;
    private Officer Officer;
    private Year Year;

    @Autowired
    ClubsRepository ClubsRepository;

    @Autowired
    YearRepository yearRepository;

    @Autowired
    OfficerRepository officerRepository;

    @Autowired
    BranchRepository branchRepository;
    
    @Autowired
    ClubTypeRepository ClubTypeRepository;


    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        ClubType = new ClubType(); 
        ClubType.setName("Club Type");
        ClubType = ClubTypeRepository.saveAndFlush(ClubType);

        Year = new Year();
        Year.setName("1");
        Year = yearRepository.saveAndFlush(Year);

        Officer = new Officer();
        Officer.setName("Name");
        Officer.setOfficerStatus(1L);
        Officer.setPassword("1234");
        Officer.setUsername("10001");
        Officer = officerRepository.saveAndFlush(Officer);

        Branch = new Branch();
        Branch.setName("Name");
        Branch = branchRepository.saveAndFlush(Branch);
    }
    @Test
    void B5827657_tesClubFull() {
        Clubs clubs = new Clubs();
        clubs.setClubName("ชมรมคอมพิวเตอร์");
        clubs.setClubPresident("นายประธาน ชมรม");
        clubs.setAdvisors("อาจารย์ที่ปรึกษาชรม");
        clubs.setObjective("วัตถุประสงค์ชมรม");
        clubs.setBranch(Branch);
        clubs.setYear(Year);
        clubs.setOfficer(Officer);
        clubs.setClubType(ClubType);

        clubs = ClubsRepository.saveAndFlush(clubs);

        Optional<Clubs> found = ClubsRepository.findById(clubs.getId());

        assertEquals("ชมรมคอมพิวเตอร์", found.get().getClubName());
        assertEquals("นายประธาน ชมรม", found.get().getClubPresident());
        assertEquals("อาจารย์ที่ปรึกษาชรม", found.get().getAdvisors());
        assertEquals("วัตถุประสงค์ชมรม", found.get().getObjective());
        assertEquals(Branch, found.get().getBranch());
        assertEquals(Year, found.get().getYear());
        assertEquals(Officer, found.get().getOfficer());
        assertEquals(ClubType, found.get().getClubType());
    }

    @Test
    void B5827657_tesClubsNameMustNotBeNull() {
        Clubs clubs = new Clubs();
        clubs.setClubName(null);
        clubs.setClubPresident("นายประธาน ชมรม");
        clubs.setAdvisors("อาจารย์ที่ปรึกษาชรม");
        clubs.setObjective("วัตถุประสงค์ชมรม");
        clubs.setId(1L);
        clubs.setBranch(Branch);
        clubs.setYear(Year);
        clubs.setOfficer(Officer);
        clubs.setClubType(ClubType);

        Set<ConstraintViolation<Clubs>> result = validator.validate(clubs);

        assertEquals(1, result.size());

        ConstraintViolation<Clubs> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ClubName", v.getPropertyPath().toString());
    }

    @Test
    void B5827657_tesClubsIdMustNotBeNull() {
        Clubs clubs = new Clubs();
        clubs.setClubName("ชมรมคอมพิวเตอร์");
        clubs.setClubPresident("นายประธาน ชมรม");
        clubs.setAdvisors("อาจารย์ที่ปรึกษาชรม");
        clubs.setObjective("วัตถุประสงค์ชมรม");
        clubs.setId(null);
        clubs.setBranch(Branch);
        clubs.setYear(Year);
        clubs.setOfficer(Officer);
        clubs.setClubType(ClubType);

        Set<ConstraintViolation<Clubs>> result = validator.validate(clubs);

        assertEquals(1, result.size());

        ConstraintViolation<Clubs> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }

    @Test
    void B5827657_tesClubsPresidentMustNotBeNull() {
        Clubs clubs = new Clubs();
        clubs.setClubName("ชมรมคอมพิวเตอร์");
        clubs.setClubPresident(null);
        clubs.setAdvisors("อาจารย์ที่ปรึกษาชรม");
        clubs.setObjective("วัตถุประสงค์ชมรม");
        clubs.setId(1L);
        clubs.setBranch(Branch);
        clubs.setYear(Year);
        clubs.setOfficer(Officer);
        clubs.setClubType(ClubType);

        Set<ConstraintViolation<Clubs>> result = validator.validate(clubs);

        assertEquals(1, result.size());

        ConstraintViolation<Clubs> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ClubPresident", v.getPropertyPath().toString());
    }

    @Test
    void B5827657_tesClubsObjectiveMustNotBeNull() {
        Clubs clubs = new Clubs();
        clubs.setClubName("ชมรมคอมพิวเตอร์");
        clubs.setClubPresident("นายประธาน ชมรม");
        clubs.setAdvisors("อาจารย์ที่ปรึกษาชรม");
        clubs.setObjective(null);
        clubs.setId(1L);
        clubs.setBranch(Branch);
        clubs.setYear(Year);
        clubs.setOfficer(Officer);
        clubs.setClubType(ClubType);

        Set<ConstraintViolation<Clubs>> result = validator.validate(clubs);

        assertEquals(1, result.size());

        ConstraintViolation<Clubs> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("Objective", v.getPropertyPath().toString());
    }

    @Test
    void B5827657_tesClubsAdvisorsMustNotBeNull() {
        Clubs clubs = new Clubs();
        clubs.setClubName("ชมรมคอมพิวเตอร์");
        clubs.setClubPresident("นายประธาน ชมรม");
        clubs.setAdvisors(null);
        clubs.setObjective("วัตถุประสงค์ชมรม");
        clubs.setId(1L);
        clubs.setBranch(Branch);
        clubs.setYear(Year);
        clubs.setOfficer(Officer);
        clubs.setClubType(ClubType);

        Set<ConstraintViolation<Clubs>> result = validator.validate(clubs);

        assertEquals(1, result.size());

        ConstraintViolation<Clubs> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("advisors", v.getPropertyPath().toString());
    }

    @Test
    void B5827657_tesClubsNameMustNotLessThan5() {
        Clubs clubs = new Clubs();
        clubs.setClubName("ชมรม");
        clubs.setClubPresident("นายประธาน ชมรม");
        clubs.setAdvisors("อาจารย์ที่ปรึกษาชรม");
        clubs.setObjective("วัตถุประสงค์ชมรม");
        clubs.setId(1L);
        clubs.setBranch(Branch);
        clubs.setYear(Year);
        clubs.setOfficer(Officer);
        clubs.setClubType(ClubType);

        Set<ConstraintViolation<Clubs>> result = validator.validate(clubs);

        assertEquals(1, result.size());

        ConstraintViolation<Clubs> v = result.iterator().next();
        assertEquals("size must be between 5 and 25", v.getMessage());
        assertEquals("ClubName", v.getPropertyPath().toString());
    }

    @Test
    void B5827657_tesClubsNameMustNotMoreThan25() {
        Clubs clubs = new Clubs();
        clubs.setClubName("ชมรมคอมพิวเตอร์เพื่อการส่งเสริมการเรียนรู้ภาษาคอมพิวเตอร์");
        clubs.setClubPresident("นายประธาน ชมรม");
        clubs.setAdvisors("อาจารย์ที่ปรึกษาชรม");
        clubs.setObjective("วัตถุประสงค์ชมรม");
        clubs.setId(1L);
        clubs.setBranch(Branch);
        clubs.setYear(Year);
        clubs.setOfficer(Officer);
        clubs.setClubType(ClubType);

        Set<ConstraintViolation<Clubs>> result = validator.validate(clubs);

        assertEquals(1, result.size());

        ConstraintViolation<Clubs> v = result.iterator().next();
        assertEquals("size must be between 5 and 25", v.getMessage());
        assertEquals("ClubName", v.getPropertyPath().toString());
    }

    @Test
    void B5827657_tesClubsPresidentMustNotHaveSpecialCharactor() {
        Clubs clubs = new Clubs();
        clubs.setClubName("ชมรมคอมพิวเตอร์");
        clubs.setClubPresident("นายประธานชมรม #%");
        clubs.setAdvisors("อาจารย์ที่ปรึกษาชมรม");
        clubs.setObjective("วัตถุประสงค์ชมรม");
        clubs.setId(1L);
        clubs.setBranch(Branch);
        clubs.setYear(Year);
        clubs.setOfficer(Officer);
        clubs.setClubType(ClubType);

        Set<ConstraintViolation<Clubs>> result = validator.validate(clubs);

        assertEquals(1, result.size());

        ConstraintViolation<Clubs> v = result.iterator().next();
        assertEquals("must match \"^[A-Za-zก-์\\s]+$\"", v.getMessage());
        assertEquals("ClubPresident", v.getPropertyPath().toString());
    }
}