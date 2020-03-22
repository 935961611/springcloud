package org.tongzhou.eurekaconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.tongzhou.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        HttpURLConnection con=null;
        try {
          URL url = new URL("http://localhost:1114/hello");
          con = (HttpURLConnection) url.openConnection();
          if (con.getResponseCode()==200){
              BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
              String s = bufferedReader.readLine();
              bufferedReader.close();
              return s;
          }
        } catch (IOException e) {
        }
          return "error";
    }
    @Autowired
    DiscoveryClient discoveryClient;
    int count=0;
    @GetMapping("/hello2")
    public  String hello2(){
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get((count++)%list.size());
        int port = instance.getPort();
        String host = instance.getHost();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("http://")
                .append(host)
                .append(":")
                .append(port)
                .append("/hello");
        HttpURLConnection con=null;
        try {
            URL url = new URL(stringBuffer.toString());
            con= ((HttpURLConnection) url.openConnection());
            if (con.getResponseCode()==200){
                BufferedReader buffer = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String s = buffer.readLine();
                buffer.close();
                return s;
            }        } catch (IOException e) {
            e.printStackTrace();
        }
          return "error";
    }
         @Autowired
         @Qualifier("restTemplate")
         RestTemplate restTemplate;

          @GetMapping("/hello3")
          public String hello3(){
           return restTemplate.getForObject("http://provider:1114/hello",String.class);
          }

    @GetMapping("/hello4")
    public void hello4(){
        String java = restTemplate.getForObject("http://provider:1114/hello2?name={1}", String.class, "java");
        System.out.println(java);
        ResponseEntity<String> java1 = restTemplate.getForEntity("http://provider/hello2", String.class, "java");
        System.out.println("body"+java1.getBody());

    }

    @GetMapping("/hello5")
    public void hello5(){
        Map<String ,Object> map = new HashMap<>();
        map.put("name","zhangsan");
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://provider/hello2?name={name}", String.class, map);
        System.out.println(forEntity.getBody());
        try {
            String url="http://provider/hello2?name="+ URLEncoder.encode("张三","UTF-8");
            URI uri = URI.create(url);
            String forObject = restTemplate.getForObject(uri, String.class);
            System.out.println(forObject);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("hello6")
    public void  hello6(){
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("name","张三");
        map.add("id","1");
        User user = restTemplate.postForObject("http://provider/user1", map, User.class);
        System.out.println(user);

        User user1 = new User();
        user1.setId(99);
        User user2 = restTemplate.postForObject("http://provider/user2", user1, User.class);
        System.out.println(user2);

    }

    @GetMapping("/hello7")
    public void  hello7(){
  MultiValueMap map= new LinkedMultiValueMap<>();
  map.add("name","zhangsan");
  map.add("id",12);
        URI uri = restTemplate.postForLocation("http://provider/register", map, User.class);
        String forObject = restTemplate.getForObject(uri, String.class);
        System.out.println(forObject);
    }
}
