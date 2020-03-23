package org.tongzhou.openfegin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OpenfeginApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenfeginApplication.class, args);
    }

}
