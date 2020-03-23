package org.tongzhou.hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {
    @Autowired
    @Qualifier("restTemplate")
    RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "error")
    @CacheResult
    public String helloService(){
        return restTemplate.getForObject("http://provider/hello",String.class);
    }

    public String error(Throwable t){
        return "error" +t.getMessage();
    }
}
