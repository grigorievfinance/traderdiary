package com.grigorievfinance.traderdiary.web.position;

import com.grigorievfinance.traderdiary.model.Position;
import com.grigorievfinance.traderdiary.service.PositionService;
import com.grigorievfinance.traderdiary.util.exception.NotFoundException;
import com.grigorievfinance.traderdiary.web.AbstractControllerTest;
import com.grigorievfinance.traderdiary.web.json.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.grigorievfinance.traderdiary.PositionTestData.*;
import static com.grigorievfinance.traderdiary.UserTestData.USER_ID;
import static com.grigorievfinance.traderdiary.UserTestData.user;
import static com.grigorievfinance.traderdiary.util.PositionUtil.createTo;
import static com.grigorievfinance.traderdiary.util.PositionUtil.getTos;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class PositionRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = PositionRestController.REST_UTL + '/';

    @Autowired
    private PositionService positionService;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + POSITION1_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(POSITION_MATCHER.contentJson(position1));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + POSITION1_ID))
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> positionService.get(POSITION1_ID, USER_ID));
    }

    @Test
    void update() throws Exception {
        Position updated = getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + POSITION1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());

        POSITION_MATCHER.assertMatch(positionService.get(POSITION1_ID, USER_ID), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        Position newposition = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newposition)))
                .andExpect(status().isCreated());

        Position created = POSITION_MATCHER.readFromJson(action);
        int newId = created.id();
        newposition.setId(newId);
        POSITION_MATCHER.assertMatch(created, newposition);
        POSITION_MATCHER.assertMatch(positionService.get(newId, USER_ID), newposition);
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(TO_MATCHER.contentJson(getTos(positions, user.getBalance())));
    }

    @Test
    void getBetween() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "filter")
                .param("startDate", "2020-01-30").param("startTime", "07:00")
                .param("endDate", "2020-01-31").param("endTime", "11:00"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(TO_MATCHER.contentJson(createTo(position5, true), createTo(position1, false)));
    }

    @Test
    void getBetweenAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "filter?startDate=&endTime="))
                .andExpect(status().isOk())
                .andExpect(TO_MATCHER.contentJson(getTos(positions, user.getBalance())));
    }
}
