package com.pharmacy.management.pms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pharmacy.management.pms.dto.HelloResponse;

@RestController
@RequestMapping("/api/v1")
public class AppController {

    @GetMapping("/hello")
    public ResponseEntity<HelloResponse> sayHello() {
        HelloResponse response = new HelloResponse();
        response.message = "Hello Java World";

        return ResponseEntity.ok(response);
    }
}
