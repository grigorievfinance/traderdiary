package com.grigorievfinance.traderdiary.service.datajpa;

import com.grigorievfinance.traderdiary.PositionTestData;
import com.grigorievfinance.traderdiary.model.Position;
import com.grigorievfinance.traderdiary.service.AbstractPositionServiceTest;
import com.grigorievfinance.traderdiary.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.grigorievfinance.traderdiary.PositionTestData.*;
import static com.grigorievfinance.traderdiary.Profiles.DATAJPA;
import static com.grigorievfinance.traderdiary.UserTestData.*;

@ActiveProfiles(DATAJPA)
class DataJpaPositionServiceTest extends AbstractPositionServiceTest {

    @Test
    void getWithUser() {
        Position adminPosition = service.getWithUser(ADMIN_POSITION_ID, ADMIN_ID);
        POSITION_MATCHER.assertMatch(adminPosition, adminPosition1);
        USER_MATCHER.assertMatch(adminPosition.getUser(), admin);
    }

    @Test
    void getWithUserNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> service.getWithUser(PositionTestData.NOT_FOUND, ADMIN_ID));
    }
}
