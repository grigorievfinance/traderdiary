package com.grigorievfinance.traderdiary.service;

import com.grigorievfinance.traderdiary.model.Position;
import com.grigorievfinance.traderdiary.util.exception.NotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;

import static com.grigorievfinance.traderdiary.PositionTestData.*;
import static com.grigorievfinance.traderdiary.UserTestData.ADMIN_ID;
import static com.grigorievfinance.traderdiary.UserTestData.USER_ID;
import static com.grigorievfinance.traderdiary.PositionTestData.getNew;
import static org.junit.Assert.assertThrows;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class PositionServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private PositionService service;

    @Test
    public void delete() {
        service.delete(POSITION1_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(POSITION1_ID, USER_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID));
    }

    @Test
    public void create() {
        Position created = service.create(getNew(), USER_ID);
        int newId = created.getId();
        Position newPosition = getNew();
        newPosition.setId(newId);
        assertMatch(created, newPosition);
        assertMatch(service.get(newId, USER_ID), newPosition);
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Position(null, position1.getDateTime(), "duplicate", 100), USER_ID));
    }

    @Test
    public void get() {
        Position actual = service.get(ADMIN_POSITION_ID, ADMIN_ID);
        assertMatch(actual, adminPosition1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, USER_ID));
    }

    @Test
    public void getNotOwn() {
        assertThrows(NotFoundException.class, () -> service.get(POSITION1_ID, ADMIN_ID));
    }

    @Test
    public void update() {
        Position updated = getUpdated();
        service.update(updated, USER_ID);
        assertMatch(service.get(POSITION1_ID, USER_ID), getUpdated());
    }

    @Test
    public void updateNotOwn() {
        assertThrows(NotFoundException.class, () -> service.update(position1, ADMIN_ID));
        assertMatch(service.get(POSITION1_ID, USER_ID), position1);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(USER_ID), positions);
    }

    @Test
    public void getBetweenInclusive() {
        assertMatch(service.getBetweenInclusive(
                        LocalDate.of(2020, Month.JANUARY, 30),
                        LocalDate.of(2020, Month.JANUARY, 30), USER_ID),
                position3, position2, position1);
    }

    @Test
    public void getBetweenWithNullDates() {
        assertMatch(service.getBetweenInclusive(null, null, USER_ID), positions);
    }
}
