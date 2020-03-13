package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import SE62.team03.Entity.Equipment;
import SE62.team03.Repository.EquipmentRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Optional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
@DataJpaTest
public class EquipmentTest {

    private Validator validator;

    @Autowired
    EquipmentRepository equipmentRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testEquipmentSave() {
        Equipment equipment = new Equipment();
        equipment.setE_id(1L);
        equipment.setE_name("ตู้เก็บของ");
        equipment = equipmentRepository.saveAndFlush(equipment);

        Optional<Equipment> result = equipmentRepository.findById(equipment.getE_id());
        
        assertEquals(1L, result.get().getE_id());
        assertEquals("ตู้เก็บของ", result.get().getE_name());
    }

    @Test
    void testEquipmentNameMustNotBeNull() {
        Equipment equipment = new Equipment();
        equipment.setE_id(1L);
        equipment.setE_name(null);

        Set<ConstraintViolation<Equipment>> result = validator.validate(equipment);

        assertEquals(1, result.size());

        ConstraintViolation<Equipment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("E_name", v.getPropertyPath().toString());
    }

    @Test
    void testEquipmentIdMustNotBeNull() {
        Equipment equipment = new Equipment();
        equipment.setE_id(null);
        equipment.setE_name("ตู้เก็บของ");

        Set<ConstraintViolation<Equipment>> result = validator.validate(equipment);

        assertEquals(1, result.size());

        ConstraintViolation<Equipment> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
    }

}
