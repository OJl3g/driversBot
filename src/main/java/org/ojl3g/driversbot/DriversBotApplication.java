package org.ojl3g.driversbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DriversBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(DriversBotApplication.class, args);
    }

}
