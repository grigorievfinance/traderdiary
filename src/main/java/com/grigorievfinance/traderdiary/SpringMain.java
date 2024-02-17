package com.grigorievfinance.traderdiary;

import com.grigorievfinance.traderdiary.model.Role;
import com.grigorievfinance.traderdiary.model.User;
import com.grigorievfinance.traderdiary.web.user.AdminRestController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(applicationContext.getBeanDefinitionNames()));
        AdminRestController adminRestController = applicationContext.getBean(AdminRestController.class);
        adminRestController.create(new User(null, "username", "email@email.com", "password", Role.ADMIN));
    }
}
