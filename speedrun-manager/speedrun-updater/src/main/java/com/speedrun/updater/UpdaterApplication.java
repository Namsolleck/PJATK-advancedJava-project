package com.speedrun.updater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication

@ComponentScan(basePackages = "com.speedrun")
@EnableJpaRepositories(basePackages = "com.speedrun.data.repositories")
@EntityScan(basePackages = "com.speedrun.data.model")
public class UpdaterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UpdaterApplication.class, args);
    }
}