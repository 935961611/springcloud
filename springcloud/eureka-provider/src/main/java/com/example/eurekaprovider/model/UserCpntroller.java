package com.example.eurekaprovider.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tongzhou.model.User;

@Controller
public class UserCpntroller {
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
