package org.tongzhou.hystrix.controller;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.tongzhou.hystrix.service.HelloCommand;
import org.tongzhou.hystrix.service.HelloService;
import org.tongzhou.hystrix.service.UserService;
import org.tongzhou.model.User;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class HelloController {
    @Autowired
    HelloService helloService;
    @GetMapping("/hello")
    public String hello(){
        return helloService.helloService();
    }
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/hellocommand")
    public void hellommand(){
        HelloCommand helloCommand = new HelloCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("tongzhou")), restTemplate);
        String execute = helloCommand.execute();
        System.out.println(execute);

        HelloCommand helloCommand1 = new HelloCommand(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("tongzhou")), restTemplate);
        try {
            Future<String> queue = helloCommand1.queue();
            String s = null;
            s = queue.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/hello2")
    public void hello2(){
        HystrixRequestContext context =  HystrixRequestContext.initializeContext();
        helloService.helloService();
        helloService.helloService();
        helloService.helloService();
        context.close();
    }

    @Autowired
    UserService userService;
    @GetMapping("/hello4")
    public void  hello4() throws ExecutionException, InterruptedException {
        HystrixRequestContext hrc = HystrixRequestContext.initializeContext();
        Future<User> userById = userService.getUserById(99);
        Future<User> userById2 = userService.getUserById(98);
        Future<User> userById3 = userService.getUserById(97);
        User user = userById.get();
        User user2 = userById2.get();
        User user3 = userById3.get();
        System.out.println(user);
        System.out.println(user2);
        System.out.println(user3);
        Thread.sleep(2000);
        Future<User> userById4 = userService.getUserById(96);
        User user4 = userById4.get();
        System.out.println(user4);


        hrc.close();

    }
}
