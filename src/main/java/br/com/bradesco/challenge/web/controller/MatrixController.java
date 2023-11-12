package br.com.bradesco.challenge.web.controller;

import br.com.bradesco.challenge.domain.entity.Matrix;
import br.com.bradesco.challenge.domain.service.IMatrixService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/matrix")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class MatrixController {

    private final IMatrixService service;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Matrix matrix) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(matrix));
    }

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