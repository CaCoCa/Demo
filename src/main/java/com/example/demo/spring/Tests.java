package com.example.demo.spring;

import com.example.demo.DemoApplication;
import com.example.demo.web.TestController;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class Tests {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class);
        TestController testController = context.getBean(TestController.class);
        testController.test();
        context.close();
    }
}
