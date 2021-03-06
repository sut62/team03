package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import SE62.team03.Entity.EquipmentRental;
import SE62.team03.Repository.EquipmentRentalRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Date;
import java.util.Optional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@DataJpaTest
public class EquipmentRentalTest {

    private Validator validator;

    @Autowired
    EquipmentRentalRepository EquipmentRentalRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5823291_testEquipmentRentalSaved() {
        EquipmentRental equipmentRental = new EquipmentRental();
        Date date = new Date();
        equipmentRental.setEquipmentRental_date(date);
        equipmentRental.setNote("Notes");
        equipmentRental.setRenter_name("renter name");
        equipmentRental.setEquipmentRental_id(1L);
        equipmentRental = EquipmentRentalRepository.saveAndFlush(equipmentRental);
        
        Optional<EquipmentRental> result = EquipmentRentalRepository.findById(equipmentRental.getEquipmentRental_id());
        
        assertEquals(1L, result.get().getEquipmentRental_id());
        assertEquals("Notes", result.get().getNote());
        assertEquals("renter name", result.get().getRenter_name());
        assertEquals(date, result.get().getEquipmentRental_date());
    }

    @Test
    void B5823291_testEquipmentRentalIdMustNotBeNull() {
        EquipmentRental equipmentRental = new EquipmentRental();
        Date date = new Date();
        equipmentRental.setEquipmentRental_date(date);
        equipmentRental.setNote("Notes");
        equipmentRental.setRenter_name("renter name");
        equipmentRental.setEquipmentRental_id(null);

        Set<ConstraintViolation<EquipmentRental>> result = validator.validate(equipmentRental);

        assertEquals(1, result.size());

        ConstraintViolation<EquipmentRental> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("EquipmentRental_id", v.getPropertyPath().toString());
    }

    @Test
    void B5823291_testEquipmentRentalDateMustNotBeNull() {
        EquipmentRental equipmentRental = new EquipmentRental();
        equipmentRental.setEquipmentRental_date(null);
        equipmentRental.setNote("Notes");
        equipmentRental.setRenter_name("renter name");
        equipmentRental.setEquipmentRental_id(1L);

        Set<ConstraintViolation<EquipmentRental>> result = validator.validate(equipmentRental);

        assertEquals(1, result.size());

        ConstraintViolation<EquipmentRental> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("EquipmentRental_date", v.getPropertyPath().toString());
    }

    @Test
    void B5823291_testNoteMustNotBeNull() {
        EquipmentRental equipmentRental = new EquipmentRental();
        Date date = new Date();
        equipmentRental.setEquipmentRental_date(date);
        equipmentRental.setNote(null);
        equipmentRental.setRenter_name("renter name");
        equipmentRental.setEquipmentRental_id(1L);

        Set<ConstraintViolation<EquipmentRental>> result = validator.validate(equipmentRental);

        assertEquals(1, result.size());

        ConstraintViolation<EquipmentRental> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }

    @Test
    void B5823291_testRenterNameMustNotBeNull() {
        EquipmentRental equipmentRental = new EquipmentRental();
        Date date = new Date();
        equipmentRental.setEquipmentRental_date(date);
        equipmentRental.setNote("notes");
        equipmentRental.setRenter_name(null);
        equipmentRental.setEquipmentRental_id(1L);

        Set<ConstraintViolation<EquipmentRental>> result = validator.validate(equipmentRental);

        assertEquals(1, result.size());

        ConstraintViolation<EquipmentRental> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("renter_name", v.getPropertyPath().toString());
    }

    @Test
    void B5823291_testRenterNameMustNotEnterNumber() {
        EquipmentRental equipmentRental = new EquipmentRental();
        Date date = new Date();
        equipmentRental.setEquipmentRental_date(date);
        equipmentRental.setNote("notes");
        equipmentRental.setRenter_name("renter name 1234");
        equipmentRental.setEquipmentRental_id(1L);

        Set<ConstraintViolation<EquipmentRental>> result = validator.validate(equipmentRental);

        assertEquals(1, result.size());

        ConstraintViolation<EquipmentRental> v = result.iterator().next();
        assertEquals("must match \"^[A-Za-zก-์\\s]+$\"", v.getMessage());
        assertEquals("renter_name", v.getPropertyPath().toString());
    }

    @Test
    void B5823291_testRenterNameMustNotHaveSpecialCharactor() {
        EquipmentRental equipmentRental = new EquipmentRental();
        Date date = new Date();
        equipmentRental.setEquipmentRental_date(date);
        equipmentRental.setNote("notes");
        equipmentRental.setRenter_name("renter name #%@%");
        equipmentRental.setEquipmentRental_id(1L);

        Set<ConstraintViolation<EquipmentRental>> result = validator.validate(equipmentRental);

        assertEquals(1, result.size());

        ConstraintViolation<EquipmentRental> v = result.iterator().next();
        assertEquals("must match \"^[A-Za-zก-์\\s]+$\"", v.getMessage());
        assertEquals("renter_name", v.getPropertyPath().toString());
    }

    @Test
    void B5823291_testNoteMustNotLessThan5() {
        EquipmentRental equipmentRental = new EquipmentRental();
        Date date = new Date();
        equipmentRental.setEquipmentRental_date(date);
        equipmentRental.setNote("note");
        equipmentRental.setRenter_name("renter name");
        equipmentRental.setEquipmentRental_id(1L);

        Set<ConstraintViolation<EquipmentRental>> result = validator.validate(equipmentRental);

        assertEquals(1, result.size());

        ConstraintViolation<EquipmentRental> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }

    @Test
    void B5823291_testNoteMustNotMoreThan30() {
        EquipmentRental equipmentRental = new EquipmentRental();
        Date date = new Date();
        equipmentRental.setEquipmentRental_date(date);
        equipmentRental.setNote("note size should be between 5 and 30 charactor");
        equipmentRental.setRenter_name("renter name");
        equipmentRental.setEquipmentRental_id(1L);

        Set<ConstraintViolation<EquipmentRental>> result = validator.validate(equipmentRental);

        assertEquals(1, result.size());

        ConstraintViolation<EquipmentRental> v = result.iterator().next();
        assertEquals("size must be between 5 and 30", v.getMessage());
        assertEquals("note", v.getPropertyPath().toString());
    }
}
