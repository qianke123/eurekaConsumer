package com.how2j.service;

import com.how2j.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class UserService {
    // ribbon的负载均衡器
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public List<User> getUsers() {
        // 获取注册中心的服务实例
        ServiceInstance serviceInstance = loadBalancerClient.choose("EUREKA-SERVICE");
        // 拼接要访问的url
        StringBuffer url = new StringBuffer();
        url.append("http://").append(serviceInstance.getHost())
                .append(":").append(serviceInstance.getPort()).append("/users");
        // 创建调用服务的RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        ParameterizedTypeReference<List<User>> type = new ParameterizedTypeReference<List<User>>() {};
        // 封装返回值的信息
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(url.toString(), HttpMethod.GET, null, type);

        List<User> list = responseEntity.getBody();
        return list;
    }
}
