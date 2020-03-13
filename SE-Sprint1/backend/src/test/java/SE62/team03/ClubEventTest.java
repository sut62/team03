package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import SE62.team03.Entity.ClubEvent;
import SE62.team03.Repository.ClubEventRepository;

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
public class ClubEventTest {

    private Validator validator;

    @Autowired
    ClubEventRepository ClubEventRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testSaveClubEvent() {
        ClubEvent clubEvent = new ClubEvent();
        clubEvent.setClubEventID(1L);
        clubEvent.setClubEventName("กิจกรรม");
        Date date = new Date();
        clubEvent.setClubEventDate(date);
        clubEvent.setClubEventPepleAmount(60L);
        clubEvent.setClubHost("Host");
        clubEvent.setClubEventStatus("รอการอนุมัติ");
        clubEvent = ClubEventRepository.saveAndFlush(clubEvent);

        Optional<ClubEvent> ClubEvent = ClubEventRepository.findById(clubEvent.getClubEventID());

        assertEquals(1L, ClubEvent.get().getClubEventID());
        assertEquals("กิจกรรม", ClubEvent.get().getClubEventName());
        assertEquals(date, ClubEvent.get().getClubEventDate());
        assertEquals(60L, ClubEvent.get().getClubEventPepleAmount());
        assertEquals("Host", ClubEvent.get().getClubHost());
        assertEquals("รอการอนุมัติ", ClubEvent.get().getClubEventStatus());
    }

    @Test
    void testClubEventIdMustNotBeNull() {
        ClubEvent clubEvent = new ClubEvent();
        clubEvent.setClubEventID(null);
        clubEvent.setClubEventName("โครงการ");
        clubEvent.setClubEventPepleAmount(60L);
        clubEvent.setClubHost("Host");
        Date date = new Date();
        clubEvent.setClubEventDate(date);
        clubEvent.setClubEventStatus("รอการอนุมัติ");
        Set<ConstraintViolation<ClubEvent>> result = validator.validate(clubEvent);
        assertEquals(1, result.size());
        ConstraintViolation<ClubEvent> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("clubEventID", v.getPropertyPath().toString());
    }

    @Test
    void tesClubEventNameMustNotBeNull() {
        ClubEvent clubEvent = new ClubEvent();
        clubEvent.setClubEventID(1L);
        clubEvent.setClubEventName(null);
        clubEvent.setClubEventPepleAmount(60L);
        clubEvent.setClubHost("Host");
        Date date = new Date();
        clubEvent.setClubEventDate(date);
        clubEvent.setClubEventStatus("รอการอนุมัติ");
        Set<ConstraintViolation<ClubEvent>> result = validator.validate(clubEvent);
        assertEquals(1, result.size());
        ConstraintViolation<ClubEvent> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ClubEventName", v.getPropertyPath().toString());
    }

    @Test
    void tesClubHostMustNotBeNull() {
        ClubEvent clubEvent = new ClubEvent();
        clubEvent.setClubEventID(1L);
        clubEvent.setClubEventName("โครงการ");
        clubEvent.setClubEventPepleAmount(60L);
        clubEvent.setClubHost(null);
        Date date = new Date();
        clubEvent.setClubEventDate(date);
        clubEvent.setClubEventStatus("รอการอนุมัติ");
        Set<ConstraintViolation<ClubEvent>> result = validator.validate(clubEvent);
        assertEquals(1, result.size());
        ConstraintViolation<ClubEvent> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ClubHost", v.getPropertyPath().toString());
    }

    @Test
    void tesClubEventDateMustNotBeNull() {
        ClubEvent clubEvent = new ClubEvent();
        clubEvent.setClubEventID(1L);
        clubEvent.setClubEventName("โครงการ");
        clubEvent.setClubEventPepleAmount(60L);
        clubEvent.setClubHost("Host");
        clubEvent.setClubEventDate(null);
        clubEvent.setClubEventStatus("รอการอนุมัติ");
        Set<ConstraintViolation<ClubEvent>> result = validator.validate(clubEvent);
        assertEquals(1, result.size());
        ConstraintViolation<ClubEvent> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ClubEventDate", v.getPropertyPath().toString());
    }

    @Test
    void B5823475_testClubEventPepleAmountMustNotBeNull() {
        ClubEvent clubEvent = new ClubEvent();
        clubEvent.setClubEventID(1L);
        clubEvent.setClubEventName("โครงการ");
        clubEvent.setClubEventPepleAmount(null);
        clubEvent.setClubEventStatus("รอการอนุมัติ");
        clubEvent.setClubHost("Host");
        Date date = new Date();
        clubEvent.setClubEventDate(date);
        Set<ConstraintViolation<ClubEvent>> result = validator.validate(clubEvent);
        assertEquals(1, result.size());
        ConstraintViolation<ClubEvent> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ClubEventPepleAmount", v.getPropertyPath().toString());
    }

    @Test
    void B5823475_testClubEventPepleAmountNotLessThan1() {
        ClubEvent clubEvent = new ClubEvent();
        clubEvent.setClubEventID(1L);
        clubEvent.setClubEventName("AAAAA");
        clubEvent.setClubEventPepleAmount(0L);
        clubEvent.setClubHost("Host");
        clubEvent.setClubEventStatus("รอการอนุมัติ");
        Date date = new Date();
        clubEvent.setClubEventDate(date);
        Set<ConstraintViolation<ClubEvent>> result = validator.validate(clubEvent);

        assertEquals(1, result.size());

        ConstraintViolation<ClubEvent> v = result.iterator().next();
        assertEquals("must be greater than or equal to 1", v.getMessage());
        assertEquals("ClubEventPepleAmount", v.getPropertyPath().toString());
    }

    @Test
    void B5823475_testClubEventPepleAmountNotMoreThan1000() {
        ClubEvent clubEvent = new ClubEvent();
        clubEvent.setClubEventID(1L);
        clubEvent.setClubEventName("AAAAA");
        clubEvent.setClubEventPepleAmount(10001L);
        clubEvent.setClubEventStatus("รอการอนุมัติ");
        clubEvent.setClubHost("Host");
        Date date = new Date();
        clubEvent.setClubEventDate(date);
        Set<ConstraintViolation<ClubEvent>> result = validator.validate(clubEvent);

        assertEquals(1, result.size());

        ConstraintViolation<ClubEvent> v = result.iterator().next();
        assertEquals("must be less than or equal to 1000", v.getMessage());
        assertEquals("ClubEventPepleAmount", v.getPropertyPath().toString());
    }

    @Test
    void tesClubEventNameMustNotLessThan5() {
        ClubEvent clubEvent = new ClubEvent();
        clubEvent.setClubEventID(1L);
        clubEvent.setClubEventName("โครง");
        clubEvent.setClubEventPepleAmount(60L);
        clubEvent.setClubHost("Host");
        clubEvent.setClubEventStatus("รอการอนุมัติ");
        Date date = new Date();
        clubEvent.setClubEventDate(date);
        Set<ConstraintViolation<ClubEvent>> result = validator.validate(clubEvent);
        assertEquals(1, result.size());
        ConstraintViolation<ClubEvent> v = result.iterator().next();
        assertEquals("size must be between 5 and 50", v.getMessage());
        assertEquals("ClubEventName", v.getPropertyPath().toString());
    }

    @Test
    void tesClubEventNameMustNotMoreThan50() {
        ClubEvent clubEvent = new ClubEvent();
        clubEvent.setClubEventID(1L);
        clubEvent.setClubEventName(
                "โครงการค่ายจาวา เตรียมน้องเพื่อพร้อมกับการเรียนรู้ภาษาจาวา และการนำไปประยุกต์ใช้ในชีวิตประจำวัน");
        clubEvent.setClubEventPepleAmount(60L);
        clubEvent.setClubHost("Host");
        clubEvent.setClubEventStatus("รอการอนุมัติ");
        Date date = new Date();
        clubEvent.setClubEventDate(date);
        Set<ConstraintViolation<ClubEvent>> result = validator.validate(clubEvent);
        assertEquals(1, result.size());
        ConstraintViolation<ClubEvent> v = result.iterator().next();
        assertEquals("size must be between 5 and 50", v.getMessage());
        assertEquals("ClubEventName", v.getPropertyPath().toString());
    }

    @Test
    void tesClubEventNameMustNotEnterSpecialCharactor() {
        ClubEvent clubEvent = new ClubEvent();
        clubEvent.setClubEventID(1L);
        clubEvent.setClubEventName("โครงการ@#% ครั้งที่ 2");
        clubEvent.setClubEventPepleAmount(60L);
        clubEvent.setClubHost("Host");
        clubEvent.setClubEventStatus("รอการอนุมัติ");
        Date date = new Date();
        clubEvent.setClubEventDate(date);
        Set<ConstraintViolation<ClubEvent>> result = validator.validate(clubEvent);
        assertEquals(1, result.size());
        ConstraintViolation<ClubEvent> v = result.iterator().next();
        assertEquals("must match \"^[0-9A-Za-zก-์\\s]+$\"", v.getMessage());
        assertEquals("ClubEventName", v.getPropertyPath().toString());
    }

    @Test
    void testClubEventStatusMustNotBeNull() {
        ClubEvent clubEvent = new ClubEvent();
        clubEvent.setClubEventID(1L);
        clubEvent.setClubEventName("โครงการ");
        clubEvent.setClubEventPepleAmount(60L);
        clubEvent.setClubHost("Host");
        Date date = new Date();
        clubEvent.setClubEventDate(date);
        clubEvent.setClubEventStatus(null);
        Set<ConstraintViolation<ClubEvent>> result = validator.validate(clubEvent);
        assertEquals(1, result.size());
        ConstraintViolation<ClubEvent> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ClubEventStatus", v.getPropertyPath().toString());
    }
}
