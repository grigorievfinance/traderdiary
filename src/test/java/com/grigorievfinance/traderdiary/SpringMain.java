package com.grigorievfinance.traderdiary;

import com.grigorievfinance.traderdiary.model.Role;
import com.grigorievfinance.traderdiary.model.User;
import com.grigorievfinance.traderdiary.web.user.AdminRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

import static com.grigorievfinance.traderdiary.TestUtil.mockAuthorize;
import static com.grigorievfinance.traderdiary.UserTestData.user;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/inmemory.xml")){
            System.out.println("Bean definition names: " + Arrays.toString(applicationContext.getBeanDefinitionNames()));
            AdminRestController adminRestController = applicationContext.getBean(AdminRestController.class);
            adminRestController.create(new User(null, "username", "email@email.com", "password", 2000, Role.ADMIN));
            mockAuthorize(user);
        }
    }
}
