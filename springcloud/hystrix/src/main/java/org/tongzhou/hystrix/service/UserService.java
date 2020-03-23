package org.tongzhou.hystrix.service;

import com.netflix.discovery.util.StringUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.tongzhou.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class UserService {
    @Autowired
    RestTemplate restTemplate;
    @HystrixCollapser(batchMethod = "getUserByIds",collapserProperties = {@HystrixProperty(name = "timerDelayInMilliseconds",value = "200")})
    public Future<User> getUserById(Integer id){
         return null;
    }
    @HystrixCommand
    public List<User> getUserByIds(List<Integer> ids){
        User[] ids1 = restTemplate.getForObject("http://provider/hello3/{1}", User[].class, StringUtils.join(ids, ","));
        return Arrays.asList(ids1);
    }
}
