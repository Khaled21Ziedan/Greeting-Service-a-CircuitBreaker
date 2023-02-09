package com.example.Greeting.Servicea.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/greeting")
public class GreetingControllerRestTemplate {
    private final RestTemplate restTemplate;

    public GreetingControllerRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public String greeting(@PathVariable String id) {
        String userName = restTemplate.getForObject("http://userManagementService/users/{id}", String.class, id);
        return "Hello " + userName;
    }
}
