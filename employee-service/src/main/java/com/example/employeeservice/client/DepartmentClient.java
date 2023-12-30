package com.example.employeeservice.client;

import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.exceptions.ExternalResourceNotFoundException;
import com.example.employeeservice.exceptions.ServiceUnavailableException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class DepartmentClient {

    private final WebClient.Builder webClientBuilder;
    private WebClient webClient;

    @Autowired
    public DepartmentClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @PostConstruct
    public void init() {
        this.webClient = webClientBuilder
                .baseUrl("http://localhost:8082/api/departments")
                .build();
    }

    public Mono<DepartmentDto> getDepartmentByCode(String code) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/get/{code}").build(code))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(new ExternalResourceNotFoundException("Cant fetch department from department service", "department",
                                response.statusCode())))
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new ServiceUnavailableException("Server error in Department Service", response.statusCode()))
                )
                .bodyToMono(DepartmentDto.class);
    }

}
