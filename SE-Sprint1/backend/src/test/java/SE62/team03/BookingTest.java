package SE62.team03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import SE62.team03.Entity.Booking;
import SE62.team03.Repository.BookingRepository;

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
public class BookingTest {

    private Validator validator;

    @Autowired
    BookingRepository bookingRepository;

    @BeforeEach
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void B5815029_tesClubFull() {
        Booking booking = new Booking();
        booking.setId(1L);
        booking.setBookingDate("2020-01-20");
        booking = bookingRepository.saveAndFlush(booking);

        Optional<Booking> found = bookingRepository.findById(booking.getId());

        assertEquals(1L, found.get().getId());
        assertEquals("2020-01-20", found.get().getBookingDate());
    }

    @Test
    void B5815029_testBookingDateMustNotBeNull() {
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
    void B5815029_testBookingIdMustNotBeNull() {
        Booking booking = new Booking();
        booking.setBookingDate("2020-01-20");
        booking.setId(null);

        Set<ConstraintViolation<Booking>> result = validator.validate(booking);

        assertEquals(1, result.size());

        ConstraintViolation<Booking> v = result.iterator().next();
        assertEquals("must not be null", v.getMessage());
        assertEquals("id", v.getPropertyPath().toString());
    }
}
