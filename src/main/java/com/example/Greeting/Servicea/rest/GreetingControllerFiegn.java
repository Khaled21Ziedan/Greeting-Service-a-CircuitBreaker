package com.example.Greeting.Servicea.rest;

import com.example.Greeting.Servicea.client.GreetingClient;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greetingfeign")
public class GreetingControllerFiegn {
    private final GreetingClient greetingClient;
    private final CircuitBreakerFactory circuitBreakerFactory;

    public GreetingControllerFiegn(GreetingClient greetingClient, CircuitBreakerFactory circuitBreakerFactory) {
        this.greetingClient = greetingClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @GetMapping("/{id}")
    public String greeting(@PathVariable String id) {
        return circuitBreakerFactory.create("hello-service").run( ()->callServiceB(id),ex->defaultgreeting());
    }

    private String callServiceB(String id) {
        if (id.equals("1000")) {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String userName = greetingClient.userGreeting(id);
        return "Hello " + userName;
    }

    private String defaultgreeting() {
        return "Hello";
    }
}
