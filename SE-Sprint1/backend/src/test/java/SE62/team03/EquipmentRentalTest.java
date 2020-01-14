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

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
    void testEquipmentRentalIdMustNotBeNull() {
        EquipmentRental equipmentRental = new EquipmentRental();
        Date date = new Date();
        equipmentRental.setEquipmentRental_date(date);
        equipmentRental.setNote("Note");
        equipmentRental.setRenter_name("renter name");
        equipmentRental.setEquipmentRental_id(null);

        Set<ConstraintViolation<EquipmentRental>> result = validator.validate(equipmentRental);

        assertEquals(1, result.size());

        ConstraintViolation<EquipmentRental> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("EquipmentRental_id", v.getPropertyPath().toString());
    }

    @Test
    void testEquipmentRentalDateMustNotBeNull() {
        EquipmentRental equipmentRental = new EquipmentRental();
        equipmentRental.setEquipmentRental_date(null);
        equipmentRental.setNote("Note");
        equipmentRental.setRenter_name("renter name");
        equipmentRental.setEquipmentRental_id(1L);

        Set<ConstraintViolation<EquipmentRental>> result = validator.validate(equipmentRental);

        assertEquals(1, result.size());

        ConstraintViolation<EquipmentRental> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("EquipmentRental_date", v.getPropertyPath().toString());
    }

    @Test
    void testNoteMustNotBeNull() {
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
    void testRenterNameMustNotBeNull() {
        EquipmentRental equipmentRental = new EquipmentRental();
        Date date = new Date();
        equipmentRental.setEquipmentRental_date(date);
        equipmentRental.setNote("note");
        equipmentRental.setRenter_name(null);
        equipmentRental.setEquipmentRental_id(1L);

        Set<ConstraintViolation<EquipmentRental>> result = validator.validate(equipmentRental);

        assertEquals(1, result.size());

        ConstraintViolation<EquipmentRental> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("renter_name", v.getPropertyPath().toString());
    }
}
