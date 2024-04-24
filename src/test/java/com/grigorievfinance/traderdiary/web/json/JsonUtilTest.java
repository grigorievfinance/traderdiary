package com.grigorievfinance.traderdiary.web.json;

import com.grigorievfinance.traderdiary.model.Position;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.grigorievfinance.traderdiary.PositionTestData.*;

public class JsonUtilTest {

    private static final Logger log = LoggerFactory.getLogger(JsonUtilTest.class);

    @Test
    void readWriteValue() {
        String json = JsonUtil.writeValue(adminPosition1);
        log.info(json);
        Position position = JsonUtil.readValue(json, Position.class);
        POSITION_MATCHER.assertMatch(position, adminPosition1);
    }

    @Test
    void readWriteValues() {
        String json = JsonUtil.writeValue(positions);
        log.info(json);
        List<Position> actual = JsonUtil.readValues(json, Position.class);
        POSITION_MATCHER.assertMatch(actual, positions);
    }
}
