package com.gencon.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.gencon.auth"})
@SpringBootApplication
public class genconApplication {
    public static void main (String[] args) {
        SpringApplication.run(genconApplication.class, args);
    }
}
