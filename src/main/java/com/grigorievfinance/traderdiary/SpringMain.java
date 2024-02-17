package com.grigorievfinance.traderdiary;

import com.grigorievfinance.traderdiary.repository.UserRepository;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(applicationContext.getBeanDefinitionNames()));
        UserRepository userRepository = applicationContext.getBean(UserRepository.class);
        userRepository.getAll();
        applicationContext.close();
    }
}
