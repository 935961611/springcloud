package org.tongzhou.openfegin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tongzhou.model.User;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RestController
public class ModelController {

    @Autowired
    HelloService modelservice;

    @GetMapping("/model")
    public void  Model() throws UnsupportedEncodingException {
        String model = this.modelservice.model();
        System.out.println(model);
        String s = modelservice.model2("张三");
        System.out.println(s);
        User user = new User();
        user.setName("李四");
        user.setId(22);
        User user1 = modelservice.model3(user);
        System.out.println(user1);
        modelservice.model4(URLEncoder.encode("王五","utf-8"));

    }
}
