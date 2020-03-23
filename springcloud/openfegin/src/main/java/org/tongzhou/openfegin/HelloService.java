package org.tongzhou.openfegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.tongzhou.model.Model;
import org.tongzhou.model.User;

@FeignClient("provider")
public interface HelloService extends Model {

    @GetMapping("/hello")
    String hello();

    @GetMapping("/user2")
    User  user2(@RequestBody User user);

    @GetMapping("/hello2")
    String hello2(@RequestParam("name") String name );
}
