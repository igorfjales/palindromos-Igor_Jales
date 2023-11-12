package br.com.bradesco.challenge.web.controller;

import br.com.bradesco.challenge.domain.service.IPalindromeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/palindrome")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PalindromeController {

    private final IPalindromeService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findById(id));
    }
}