package net.javaguides.departmentservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class MessageController {

    @Value("${spring.boot.message}")
    private String message;

    @GetMapping("message")
    public String getMessage() {
        return message;
    }

    @GetMapping("lb/port")
    public ResponseEntity<String> getAppPort(HttpServletRequest request) {
        int port = request.getServerPort();
        return ResponseEntity.ok("Application is running on port: " + port);

    }
}
