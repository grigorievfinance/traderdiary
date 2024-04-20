package com.grigorievfinance.traderdiary.web.user;

import com.grigorievfinance.traderdiary.repository.inmemory.InMemoryUserRepository;
import com.grigorievfinance.traderdiary.util.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static com.grigorievfinance.traderdiary.UserTestData.NOT_FOUND;
import static com.grigorievfinance.traderdiary.UserTestData.USER_ID;

@SpringJUnitConfig(locations = {"classpath:spring/inmemory.xml"})
class InMemoryAdminRestControllerSpringTest {
    @Autowired
    private AdminRestController controller;

    @Autowired
    private InMemoryUserRepository repository;

    @BeforeEach
    void setup() {
        repository.init();
    }

    @Test
    void delete() {
        controller.delete(USER_ID);
        Assertions.assertNull(repository.get(USER_ID));
    }

    @Test
    void deleteNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> controller.delete(NOT_FOUND));
    }
}
