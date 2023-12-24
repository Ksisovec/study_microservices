package net.javaguides.departmentservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    @Value("$spring.boot.message")
    private String message;
}
