package com.grigorievfinance.traderdiary.service;

import com.grigorievfinance.traderdiary.model.Position;
import com.grigorievfinance.traderdiary.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;

import static com.grigorievfinance.traderdiary.PositionTestData.*;
import static com.grigorievfinance.traderdiary.UserTestData.ADMIN_ID;
import static com.grigorievfinance.traderdiary.UserTestData.USER_ID;
import static java.time.LocalDateTime.of;
import static org.junit.jupiter.api.Assertions.assertThrows;

public abstract class AbstractPositionServiceTest extends AbstractServiceTest {

    @Autowired
    protected PositionService service;

    @Test
    void delete() {
        service.delete(POSITION1_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(POSITION1_ID, USER_ID));
    }

    @Test
    void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID));
    }

    @Test
    void deleteNotOwn() {
        assertThrows(NotFoundException.class, () -> service.delete(POSITION1_ID, ADMIN_ID));
    }

    @Test
    void create() {
        Position created = service.create(getNew(), USER_ID);
        int newId = created.id();
        Position newMeal = getNew();
        newMeal.setId(newId);
        POSITION_MATCHER.assertMatch(created, newMeal);
        POSITION_MATCHER.assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test
    void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Position(null, position1.getDateTime(), "duplicate", 100), USER_ID));
    }

    @Test
    void get() {
        Position actual = service.get(ADMIN_POSITION_ID, ADMIN_ID);
        POSITION_MATCHER.assertMatch(actual, adminPosition1);
    }

    @Test
    void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, USER_ID));
    }

    @Test
    void getNotOwn() {
        assertThrows(NotFoundException.class, () -> service.get(POSITION1_ID, ADMIN_ID));
    }

    @Test
    void update() {
        Position updated = getUpdated();
        service.update(updated, USER_ID);
        POSITION_MATCHER.assertMatch(service.get(POSITION1_ID, USER_ID), getUpdated());
    }

    @Test
    void updateNotOwn() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.update(getUpdated(), ADMIN_ID));
        Assertions.assertEquals("Not found entity with id=" + POSITION1_ID, exception.getMessage());
        POSITION_MATCHER.assertMatch(service.get(POSITION1_ID, USER_ID), position1);
    }

    @Test
    void getAll() {
        POSITION_MATCHER.assertMatch(service.getAll(USER_ID), positions);
    }

    @Test
    void getBetweenInclusive() {
        POSITION_MATCHER.assertMatch(service.getBetweenInclusive(
                        LocalDate.of(2020, Month.JANUARY, 30),
                        LocalDate.of(2020, Month.JANUARY, 30), USER_ID),
                position3, position2, position1);
    }

    @Test
    void getBetweenWithNullDates() {
        POSITION_MATCHER.assertMatch(service.getBetweenInclusive(null, null, USER_ID), positions);
    }

    @Test
    void createWithException() {
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Position(null, of(2015, Month.JUNE, 1, 18, 0), "  ", 300), USER_ID));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Position(null, null, "Description", 300), USER_ID));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Position(null, of(2015, Month.JUNE, 1, 18, 0), "Description", 9), USER_ID));
        validateRootCause(ConstraintViolationException.class, () -> service.create(new Position(null, of(2015, Month.JUNE, 1, 18, 0), "Description", 5001), USER_ID));
    }
}
