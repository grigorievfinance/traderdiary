package com.grigorievfinance.traderdiary.service;

import com.grigorievfinance.traderdiary.model.Position;
import com.grigorievfinance.traderdiary.util.exception.NotFoundException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.TimeUnit;

import static com.grigorievfinance.traderdiary.PositionTestData.*;
import static com.grigorievfinance.traderdiary.UserTestData.ADMIN_ID;
import static com.grigorievfinance.traderdiary.UserTestData.USER_ID;
import static com.grigorievfinance.traderdiary.PositionTestData.getNew;
import static org.junit.Assert.assertThrows;
import static org.slf4j.LoggerFactory.getLogger;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class PositionServiceTest {

    private static final Logger log = getLogger("result");

    private static final StringBuilder results = new StringBuilder();

    @Rule
    public final Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("\n%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result);
            log.info(result + " ms\n");
        }
    };


    @Autowired
    private PositionService service;

    @AfterClass
    public static void printResult() {
        log.info("\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }

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
        POSITION_MATCHER.assertMatch(created, newPosition);
        POSITION_MATCHER.assertMatch(service.get(newId, USER_ID), newPosition);
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Position(null, position1.getDateTime(), "duplicate", 100), USER_ID));
    }

    @Test
    public void get() {
        Position actual = service.get(ADMIN_POSITION_ID, ADMIN_ID);
        POSITION_MATCHER.assertMatch(actual, adminPosition1);
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
        POSITION_MATCHER.assertMatch(service.get(POSITION1_ID, USER_ID), getUpdated());
    }

    @Test
    public void updateNotOwn() {
        NotFoundException exception = assertThrows(NotFoundException.class, () -> service.update(position1, ADMIN_ID));
        Assert.assertEquals("Not found entity with id=" + POSITION1_ID, exception.getMessage());
        POSITION_MATCHER.assertMatch(service.get(POSITION1_ID, USER_ID), position1);
    }

    @Test
    public void getAll() {
        POSITION_MATCHER.assertMatch(service.getAll(USER_ID), positions);
    }

    @Test
    public void getBetweenInclusive() {
        POSITION_MATCHER.assertMatch(service.getBetweenInclusive(
                        LocalDate.of(2020, Month.JANUARY, 30),
                        LocalDate.of(2020, Month.JANUARY, 30), USER_ID),
                position3, position2, position1);
    }

    @Test
    public void getBetweenWithNullDates() {
        POSITION_MATCHER.assertMatch(service.getBetweenInclusive(null, null, USER_ID), positions);
    }
}
