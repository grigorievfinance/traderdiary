package com.grigorievfinance.traderdiary.web;

import com.grigorievfinance.traderdiary.UserTestData;
import org.junit.Test;

import static com.grigorievfinance.traderdiary.model.AbstractBaseEntity.START_SEQ;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

public class RootControllerTest  extends AbstractControllerTest {

    @Test
    public void getUsers() throws Exception {
        perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users", hasSize(3)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(START_SEQ)),
                                hasProperty("name", is(UserTestData.user.getName()))
                        )
                )));
    }
}
