package com.example.scheduledevelopment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ScheduleDevelopmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleDevelopmentApplication.class, args);
    }

}
