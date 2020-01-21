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
    void tesClubEventIdMustNotBeNull() {
        ClubEvent clubEvent = new ClubEvent();
        clubEvent.setClubEventID(null);
        clubEvent.setClubEventName("โครงการ");
        clubEvent.setClubEventPepleAmount(60L);
        clubEvent.setClubHost("Host");
        clubEvent.setClubEventDate("10-01-2020");
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
        clubEvent.setClubEventDate("10-01-2020");
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
        clubEvent.setClubEventDate("10-01-2020");
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
        Set<ConstraintViolation<ClubEvent>> result = validator.validate(clubEvent);

        assertEquals(1, result.size());

        ConstraintViolation<ClubEvent> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ClubEventDate", v.getPropertyPath().toString());
    }

    @Test
    void testClubEventPepleAmountMustNotBeNull() {
        ClubEvent clubEvent = new ClubEvent();
        clubEvent.setClubEventID(1L);
        clubEvent.setClubEventName("โครงการ");
        clubEvent.setClubEventPepleAmount(null);
        clubEvent.setClubHost("Host");
        clubEvent.setClubEventDate("10-01-2020");
        Set<ConstraintViolation<ClubEvent>> result = validator.validate(clubEvent);

        assertEquals(1, result.size());

        ConstraintViolation<ClubEvent> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("ClubEventPepleAmount", v.getPropertyPath().toString());
    }

    // @Test
    // void testClubEventDateTimeFormatpatternl() {
    //     ClubEvent clubEvent = new ClubEvent();
    //     clubEvent.setClubEventID(1L);
    //     clubEvent.setClubEventName("โครงการ");
    //     clubEvent.setClubEventPepleAmount(62L);
    //     clubEvent.setClubHost("Host");
    //     clubEvent.setClubEventDate("100-01-2020");
    //     Set<ConstraintViolation<ClubEvent>> result = validator.validate(clubEvent);
    //     assertEquals(1, result.size());
    //     ConstraintViolation<ClubEvent> v = result.iterator().next();
    //     assertEquals("DateTimeFormat", v.getMessage());
    //     assertEquals("ClubEventPepleAmount", v.getPropertyPath().toString());
    // }

    
}
