package com.example.eurekaprovider.model;


import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.tongzhou.model.User;

import java.net.URLDecoder;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {
    @Value("${server.port}")
    Integer port;
    @GetMapping("/hello")
    public String hello(){
        System.out.println("端口号:"+port);
        return "hello eureka:"+port;
    }

    @GetMapping("/hello2")
    public String hello2(String name){
        String decode = URLDecoder.decode(name);
        return "hello:"+decode;
    }


     //请求合并的接口
   @GetMapping("/hello3/{ids}")
    public List<User> hello3(@PathVariable String ids){
       String[] split = ids.split(",");
       List<User> users= new ArrayList<>();
       for (String s : split) {
           User user = new User();
           user.setId(Integer.parseInt(s));
           users.add(user);
       }
       return users;
   }

}
