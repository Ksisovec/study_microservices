package com.example.employeeservice.client;

import com.example.employeeservice.dto.OrganizationDto;
import com.example.employeeservice.exceptions.ExternalResourceNotFoundException;
import com.example.employeeservice.exceptions.ServiceUnavailableException;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class OrganizationClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentClient.class);
    private final WebClient.Builder webClientBuilder;
    private WebClient webClient;

    @Autowired
    public OrganizationClient(@Qualifier("lbWebClient") WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @PostConstruct
    public void init() {
        this.webClient = webClientBuilder
                .baseUrl("http://ORGANIZATION-SERVICE/api/organization")
//                .baseUrl("http://localhost:8083/api/organization")
                .build();
    }

    public Mono<OrganizationDto> getOrganizationByCode(String code) {
        LOGGER.info("Getting organization by code {}", code);
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/{code}").build(code))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(new ExternalResourceNotFoundException("Can't fetch organization from organization service", "organization",
                                response.statusCode())))
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new ServiceUnavailableException("Server error in Organization Service", response.statusCode()))
                )
                .bodyToMono(OrganizationDto.class);
    }
}
