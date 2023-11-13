package br.com.bradesco.challenge.web.controller;

import br.com.bradesco.challenge.domain.model.Palindrome;
import br.com.bradesco.challenge.domain.service.IPalindromeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/palindrome")
public class PalindromeController {

    private final IPalindromeService service;

    public PalindromeController(IPalindromeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Palindrome>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Palindrome> findById(@PathVariable UUID id) {
        return service.findById(id)
                .map(palindrome -> ResponseEntity.ok().body(palindrome))
                .orElse(ResponseEntity.ok().build());
    }
}