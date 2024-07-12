package com.crud.springbootcrud;

import org.egov.tracer.config.TracerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.crud.springbootcrud.repo"})
public class SpringBootCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudApplication.class, args);
    }

}
