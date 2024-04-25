package com.grigorievfinance.traderdiary.service.datajpa;

import com.grigorievfinance.traderdiary.model.User;
import com.grigorievfinance.traderdiary.service.AbstractUserServiceTest;
import com.grigorievfinance.traderdiary.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.grigorievfinance.traderdiary.Profiles.DATAJPA;
import static com.grigorievfinance.traderdiary.UserTestData.*;

@ActiveProfiles(DATAJPA)
class DataJpaUserServiceTest extends AbstractUserServiceTest {

    @Test
    void getWithPositions() {
        User actual = service.getWithPositions(ADMIN_ID);
        USER_WITH_POSITIONSS_MATCHER.assertMatch(actual, admin);
    }

    @Test
    void getWithPositionsNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> service.getWithPositions(NOT_FOUND));
    }
}
