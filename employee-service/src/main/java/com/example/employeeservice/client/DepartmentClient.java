package com.example.employeeservice.client;

import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.exceptions.ExternalResourceNotFoundException;
import com.example.employeeservice.exceptions.ServiceUnavailableException;
import io.github.resilience4j.retry.annotation.Retry;
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
public class DepartmentClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentClient.class);
    private final WebClient.Builder webClientBuilder;
    private WebClient webClient;

    @Autowired
    public DepartmentClient(@Qualifier("lbWebClient") WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @PostConstruct
    public void init() {
        this.webClient = webClientBuilder
                .baseUrl("http://DEPARTMENT-SERVICE/api/departments")
                .build();
    }

    //    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    @Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
    public Mono<DepartmentDto> getDepartmentByCode(String code) {
        LOGGER.info("Getting department by code {}", code);
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder.path("/get/{code}").build(code))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(new ExternalResourceNotFoundException("Can't fetch department from department service", "department",
                                response.statusCode())))
                .onStatus(HttpStatusCode::is5xxServerError, response ->
                        Mono.error(new ServiceUnavailableException("Server error in Department Service", response.statusCode()))
                )
                .bodyToMono(DepartmentDto.class);
    }

    public Mono<DepartmentDto> getDefaultDepartment(String code, Throwable throwable) {
        LOGGER.info("Getting department fallback method was invoked");
        return Mono.fromSupplier(() -> {
            DepartmentDto departmentDto = new DepartmentDto();
            departmentDto.setDepartmentName("R&D Department");
            departmentDto.setDepartmentCode("default code");
            departmentDto.setDepartmentDescription("Default department description");
            return departmentDto;
        });
    }

}
