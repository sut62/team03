package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import SE62.team03.Entity.Booking;
import SE62.team03.Repository.BookingRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class BookingTest {

    private Validator validator;

    @Autowired
    BookingRepository BookingRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void tesBookingDateMustNotBeNull() {
        Booking booking = new Booking();
        booking.setBookingDate(null);
        booking.setId(1L);
        Set<ConstraintViolation<Booking>> result = validator.validate(booking);

        assertEquals(1, result.size());

        ConstraintViolation<Booking> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("bookingDate", v.getPropertyPath().toString());
    }

    @Test
    void testBookingIdMustNotBeNull() {
        Booking booking = new Booking();
        Date date = new Date();
        booking.setBookingDate(date);
        booking.setId(null);

        Set<ConstraintViolation<Booking>> result = validator.validate(booking);

        assertEquals(1, result.size());

        ConstraintViolation<Booking> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }
}
