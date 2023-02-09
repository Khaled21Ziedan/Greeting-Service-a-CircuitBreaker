package com.example.Greeting.Servicea.rest;

import com.example.Greeting.Servicea.client.GreetingClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetingfeign")
public class GreetingControllerFiegn {
    private final GreetingClient greetingClient;

    public GreetingControllerFiegn(GreetingClient greetingClient) {
        this.greetingClient = greetingClient;
    }

    @GetMapping("/{id}")
    public String greeting(@PathVariable String id) {
        String userName = greetingClient.userGreeting(id);
        return "Hello " + userName;
    }
}
