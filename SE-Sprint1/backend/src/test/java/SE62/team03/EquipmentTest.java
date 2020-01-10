package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

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
        assertEquals("E_id", v.getPropertyPath().toString());
    }

   
    // @Test
    // void testPersonIdOKWith13Digits() {
    // Person person = new Person();
    // person.setPersonId("1234567890123");

    // person = personRepository.saveAndFlush(person);

    // Optional<Person> found = personRepository.findById(person.getId());
    // assertEquals("1234567890123", found.get().getPersonId());
    // }
    // @Test
    // void testPersonIdMustNotBe12Digits() {
    // Person person = new Person();
    // person.setPersonId("123456789012"); // 12 digits

    // Set<ConstraintViolation<Person>> result = validator.validate(person);

    // // result ต้องมี error 1 ค่าเท่านั้น
    // assertEquals(1, result.size());

    // // error message ตรงชนิด และถูก field
    // ConstraintViolation<Person> v = result.iterator().next();
    // assertEquals("must match \"\\d{13}\"", v.getMessage());
    // assertEquals("personId", v.getPropertyPath().toString());
    // }

    // @Test
    // void testPersonIdMustNotBe14Digits() {
    // Person person = new Person();
    // person.setPersonId("12345678901234"); // 14 digits

    // Set<ConstraintViolation<Person>> result = validator.validate(person);

    // // result ต้องมี error 1 ค่าเท่านั้น
    // assertEquals(1, result.size());

    // // error message ตรงชนิด และถูก field
    // ConstraintViolation<Person> v = result.iterator().next();
    // assertEquals("must match \"\\d{13}\"", v.getMessage());
    // assertEquals("personId", v.getPropertyPath().toString());
    // }

    // @Test
    // void testPersonIdMustBeUnique() {
    //     // สร้าง person object
    //     Person p1 = new Person();
    //     p1.setPersonId("1234567890123");
    //     personRepository.saveAndFlush(p1);

    //     // คาดหวังว่า DataIntegrityViolationException จะถูก throw
    //     assertThrows(DataIntegrityViolationException.class, () -> {
    //         // สร้าง person object ตัวที่ 2
    //         Person p2 = new Person();
    //         p2.setPersonId("1234567890123");
    //         personRepository.saveAndFlush(p2);
    //     });
    // }

}
