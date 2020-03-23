package org.tongzhou.model;

import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

public interface Model {
    @GetMapping("/model1")
      String model();
    @GetMapping("/model2")
    String model2(@RequestParam("name")String name);
    @PostMapping("/model3")
    User model3(@RequestBody User user);
    @GetMapping("/model4")
    void model4(@RequestHeader("name") String name) throws UnsupportedEncodingException;
}
