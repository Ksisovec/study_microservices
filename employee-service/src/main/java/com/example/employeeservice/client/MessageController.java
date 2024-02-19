package com.example.employeeservice.client;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RefreshScope
@RestController
public class MessageController {
    private final WebClient.Builder webClientBuilder;
    private WebClient webClient;

    @Autowired
    public MessageController(@Qualifier("lbWebClient") WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @PostConstruct
    public void init() {
        this.webClient = webClientBuilder
                .baseUrl("http://DEPARTMENT-SERVICE/")
                .build();
    }

    @Value("${spring.boot.message}")
    private String message;

    @GetMapping("/users/message")
    public String getMessage() {
        return message;
    }

    @GetMapping("/lb/client/port")
    public String loadBalancerCheck() {
        return webClient
                .get()
                .uri("lb/port")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
