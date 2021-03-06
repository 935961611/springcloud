package org.tongzhou.hystrix.service;

import com.netflix.hystrix.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class HelloCommand extends HystrixCommand<String> {
    RestTemplate restTemplate;

    public HelloCommand(Setter setter, RestTemplate restTemplate) {
        super(setter);
        this.restTemplate = restTemplate;
    }

    @Override
    protected String run() throws Exception {
        int i=1/0;
        return restTemplate.getForObject("http://provider/hello",String.class);
    }

    @Override
    protected String getFallback() {
        return "error-extends"+getExecutionException().getMessage();
    }
}
