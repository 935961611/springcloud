package org.tongzhou.resilience4j;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Service
@Retry(name = "retryA")
@CircuitBreaker(name = "cba",fallbackMethod = "error")
public class HelloService {
    @Autowired
    RestTemplate restTemplate;
    public String  hello(){
        System.out.println("aaa");
        int i=1/0;

     return    restTemplate.getForObject("http://provider/hello",String.class);
    }

    public String error(Throwable t){
        return "error";
    }

}
