package com.example.eurekaprovider.model;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.tongzhou.model.User;

import java.net.URLDecoder;
import java.security.PublicKey;

@RestController
public class HelloController {
    @Value("${server.port}")
    Integer port;
    @GetMapping("/hello")
    public String hello(){
        return "hello eureka:"+port;
    }

    @GetMapping("/hello2")
    public String hello2(String name){
        String decode = URLDecoder.decode(name);
        return "hello:"+decode;
    }

    @PostMapping("/user1")
    public User addUser1(User user){

        return user;
    }
    @PostMapping("/user2")
    public User adduser2(@RequestBody User user){
        return user;

    }}
