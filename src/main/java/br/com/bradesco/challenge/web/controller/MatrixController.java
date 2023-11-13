package br.com.bradesco.challenge.web.controller;

import br.com.bradesco.challenge.domain.model.Matrix;
import br.com.bradesco.challenge.domain.service.IMatrixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/matrix")
public class MatrixController {

    private final IMatrixService service;

    public MatrixController(IMatrixService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Matrix> save(@RequestBody Matrix matrix) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(matrix));
    }

    @GetMapping
    public ResponseEntity<List<Matrix>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matrix> findById(@PathVariable UUID id) {
        return service.findById(id)
                .map(matrix -> ResponseEntity.ok().body(matrix))
                .orElse(ResponseEntity.ok().build());
    }
}