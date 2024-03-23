package com.grigorievfinance.traderdiary.service.datajpa;

import com.grigorievfinance.traderdiary.PositionTestData;
import com.grigorievfinance.traderdiary.UserTestData;
import com.grigorievfinance.traderdiary.model.User;
import com.grigorievfinance.traderdiary.service.AbstractUserServiceTest;
import com.grigorievfinance.traderdiary.util.exception.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import static com.grigorievfinance.traderdiary.PositionTestData.POSITION_MATCHER;
import static com.grigorievfinance.traderdiary.Profiles.DATAJPA;
import static com.grigorievfinance.traderdiary.UserTestData.*;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {
    @Test
    public void getWithPositions() {
        User user = service.getWithPositions(USER_ID);
        USER_MATCHER.assertMatch(user, UserTestData.user);
        POSITION_MATCHER.assertMatch(user.getPositions(), PositionTestData.positions);
    }

    @Test
    public void getWithPositionsNotFound() {
        Assert.assertThrows(NotFoundException.class, () -> service.getWithPositions(NOT_FOUND));
    }
}
