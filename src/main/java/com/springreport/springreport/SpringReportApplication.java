package com.springreport.springreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringReportApplication.class, args);
    }

}
