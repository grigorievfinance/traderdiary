package com.grigorievfinance.traderdiary.web;

import com.grigorievfinance.traderdiary.model.User;
import org.assertj.core.matcher.AssertionMatcher;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.grigorievfinance.traderdiary.UserTestData.*;
import static com.grigorievfinance.traderdiary.PositionTestData.positions;
import static com.grigorievfinance.traderdiary.util.PositionUtil.getTos;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RootControllerTest  extends AbstractControllerTest {

    @Test
    void getUsers() throws Exception {
        perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users",
                        new AssertionMatcher<List<User>>() {
                            @Override
                            public void assertion(List<User> actual) throws AssertionError {
                                USER_MATCHER.assertMatch(actual, admin, guest, user);
                            }
                        }
                ));
    }

    @Test
    void getpositions() throws Exception {
        perform(get("/positions"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("positions"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/positions.jsp"))
                .andExpect(model().attribute("positions", getTos(positions, SecurityUtil.authUserMaxLoss())));
    }
}
