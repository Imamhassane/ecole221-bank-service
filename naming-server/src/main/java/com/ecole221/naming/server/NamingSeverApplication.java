package com.ecole221.naming.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class NamingSeverApplication {
    public static void main(String[] args) {
        SpringApplication.run(NamingSeverApplication.class , args);
    }
}
