package com.krishna.runmate;

import com.krishna.runmate.run.Location;
import com.krishna.runmate.run.Run;
import com.krishna.runmate.run.RunRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class RunMateApplication {

    private static final Logger log = LoggerFactory.getLogger(RunMateApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RunMateApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(RunRepository runRepository) {
        return args -> {
            Run run = new Run(1, "First Run", LocalDateTime.now(), LocalDateTime.now().plusHours(1), 5, Location.OUTDOOR);
            runRepository.create(run);
        };
    }
}