package org.tongzhou.openfegin;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tongzhou.model.User;

import javax.lang.model.type.ExecutableType;
import java.io.UnsupportedEncodingException;

@Component
@RequestMapping("/tongzhou")
public class HelloServiceFallBack implements HelloService {
    @Override
    public String hello() {
        return "error";
    }

    @Override
    public User user2(User user) {
        return null;
    }

    @Override
    public String hello2(String name) {
        return null;
    }

    @Override
    public String model() {
        return null;
    }

    @Override
    public String model2(String name) {
        return null;
    }

    @Override
    public User model3(User user) {
        return null;
    }

    @Override
    public void model4(String name) throws UnsupportedEncodingException {

    }
}
