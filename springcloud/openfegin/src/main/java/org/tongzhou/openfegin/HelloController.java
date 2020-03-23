package org.tongzhou.openfegin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tongzhou.model.User;

@RestController
public class HelloController {
    @Autowired
    HelloService helloService;
    @GetMapping("/hello")
    public void  hello(){
        String hello = helloService.hello();
        System.out.println(hello);

    }

     @GetMapping("/hello2")
    public void  hello2(){
         String s = helloService.hello2("张三");
         System.out.println(s);
         User user = new User();
         user.setId(1);
         user.setName("李四");
         User user1 = helloService.user2(user);
         System.out.println(user1);

     }
}
