package org.tongzhou.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
    /*@Bean
    RouteLocator routeLocator(RouteLocatorBuilder builder){
      return   builder.routes().route("java_rout",r ->r.path("/get")
              .uri("https://github.com/prometheus/prometheus/releases/tag/v2.17.0-rc.4")).build();
    }*/

}
