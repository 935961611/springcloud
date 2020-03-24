package org.tongzhou.resilience4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HelloService helloService;
    @GetMapping("/hello")
    public void hello(){
        String hello = helloService.hello();
        System.out.println(hello);
    }
    @GetMapping("/hello2")
    public void hello2(){
        for (int i = 0; i <5 ; i++) {
            restTemplate.getForObject("http://localhost:1114/hello",String.class);
        }
        System.out.println("success");
    }
}
