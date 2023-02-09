package com.example.Greeting.Servicea.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "userManagementService")
public interface GreetingClient {
    @GetMapping("/users/{id}")
    public String userGreeting(@PathVariable String id);
}
