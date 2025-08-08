package edu.mikita.apiintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "edu.mikita.apiintegration.api")
@SpringBootApplication
public class ApiIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiIntegrationApplication.class, args);
    }

}
