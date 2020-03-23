package com.example.eurekaprovider.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.tongzhou.model.User;

@RestController
public class UserCpntroller {

    @PostMapping("/user1")
    public User addUser1(User user){

        return user;
    }
    @PostMapping("/user2")
    public User adduser2(@RequestBody User user){
        return user;

    }
    @PostMapping("/register")
    public String register(User user){
        return "redirect:http://provider/loginpage?name="+user.getName();

    }

      @GetMapping("/loginpage")
      @ResponseBody
    public String loginpage(String name){
        return "loginpage="+name;
      }
}
