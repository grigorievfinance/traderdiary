package com.grigorievfinance.traderdiary;

import com.grigorievfinance.traderdiary.model.Position;

import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static com.grigorievfinance.traderdiary.model.AbstractBaseEntity.START_SEQ;
import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;

public class PositionTestData {
    public static final int NOT_FOUND = 10;
    public static final int POSITION1_ID = START_SEQ + 3;
    public static final int ADMIN_POSITION_ID = START_SEQ + 10;

    public static final Position position1 = new Position(POSITION1_ID, of(2020, Month.JANUARY, 30, 10, 0), "BNBUSDT", 500);
    public static final Position position2 = new Position(POSITION1_ID + 1, of(2020, Month.JANUARY, 30, 13, 0), "BNBUSDT", 1000);
    public static final Position position3 = new Position(POSITION1_ID + 2, of(2020, Month.JANUARY, 30, 20, 0), "BNBUSDT", 500);
    public static final Position position4 = new Position(POSITION1_ID + 3, of(2020, Month.JANUARY, 31, 0, 0), "BNBUSDT", 100);
    public static final Position position5 = new Position(POSITION1_ID + 4, of(2020, Month.JANUARY, 31, 10, 0), "BNBUSDT", 500);
    public static final Position position6 = new Position(POSITION1_ID + 5, of(2020, Month.JANUARY, 31, 13, 0), "BNBUSDT", 1000);
    public static final Position position7 = new Position(POSITION1_ID + 6, of(2020, Month.JANUARY, 31, 20, 0), "BNBUSDT", 510);
    public static final Position adminPosition1 = new Position(ADMIN_POSITION_ID, of(2020, Month.JANUARY, 31, 14, 0), "BTCUSDT", 510);
    public static final Position adminPosition2 = new Position(ADMIN_POSITION_ID + 1, of(2020, Month.JANUARY, 31, 21, 0), "ETHUSDT", 1500);

    public static final List<Position> positions = Arrays.asList(position7, position6, position5, position4, position3, position2, position1);

    public static Position getNew() {
        return new Position(null, of(2020, Month.FEBRUARY, 1, 18, 0), "ETHUSDT", 300);
    }

    public static Position getUpdated() {
        return new Position(POSITION1_ID, position1.getDateTime().plus(2, ChronoUnit.MINUTES), "BTCUSDT", 200);
    }

    public static void assertMatch(Position actual, Position expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Position> actual, Position... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Position> actual, Iterable<Position> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparator().isEqualTo(expected);
    }
}
