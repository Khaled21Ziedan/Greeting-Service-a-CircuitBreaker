package com.example.Greeting.Servicea.rest;

import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/greeting")
public class GreetingControllerRestTemplate {
    private final RestTemplate restTemplate;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public GreetingControllerRestTemplate(RestTemplate restTemplate, CircuitBreakerFactory circuitBreakerFactory) {
        this.restTemplate = restTemplate;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @GetMapping("/{id}")
    public String greetingUserName(@PathVariable String id) {
        String userName = restTemplate.getForObject("http://userManagementService/users/{id}", String.class, id);
        return "Hello " + userName;
    }

    @GetMapping("name/{name}")
    public String greetingName(@PathVariable String name) {
        return circuitBreakerFactory.create("hello-internal").run(() -> sayHello(name), ex -> defaultHello());
    }

    private String sayHello(String userName) {
        if (userName.equals("Khaled")) {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Hello " + userName;
    }

    private String defaultHello() {
        return "Hello";
    }
}
