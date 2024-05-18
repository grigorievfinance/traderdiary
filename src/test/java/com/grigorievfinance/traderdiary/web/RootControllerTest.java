package com.grigorievfinance.traderdiary.web;

import org.junit.jupiter.api.Test;

import static com.grigorievfinance.traderdiary.TestUtil.userAuth;
import static com.grigorievfinance.traderdiary.TestUtil.userHttpBasic;
import static com.grigorievfinance.traderdiary.UserTestData.admin;
import static com.grigorievfinance.traderdiary.UserTestData.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RootControllerTest  extends AbstractControllerTest {

    @Test
    void getUsers() throws Exception {
        perform(get("/users")
                .with(userAuth(admin)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"));
    }

    @Test
    void unAuth() throws Exception {
        perform(get("/users"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    void getPositions() throws Exception {
        perform(get("/positions")
                .with(userHttpBasic(user)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("positions"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/positions.jsp"));
    }
}
