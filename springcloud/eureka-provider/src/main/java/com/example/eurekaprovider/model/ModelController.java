package com.example.eurekaprovider.model;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.tongzhou.model.Model;
import org.tongzhou.model.User;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
@RestController
public class ModelController implements Model {
    @Value("${server.port}")
    Integer port;

    @Override
    public String model() {
        return "端口号："+port;
    }

    @Override
    public String model2(String name) {
        return name;
    }

    @Override
    public User model3(User user) {
        return user;
    }

    @Override
    public void model4(@RequestHeader String name) throws UnsupportedEncodingException {
        System.out.println(URLDecoder.decode(name,"utf-8"));
    }
}
