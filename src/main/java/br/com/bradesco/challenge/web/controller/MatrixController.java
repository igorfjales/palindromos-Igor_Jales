package br.com.bradesco.challenge.web.controller;

import br.com.bradesco.challenge.domain.model.Matrix;
import br.com.bradesco.challenge.domain.service.IMatrixService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/matrix", produces = {"application/json"})
@Tag(name = "Matrix")
public class MatrixController {

    private final IMatrixService service;

    public MatrixController(IMatrixService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Receive a matrix of characters, identify palindromes in all directions, and save the results in the database.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Matrix saved successfully with identified palindromes."),
            @ApiResponse(responseCode = "400", description = "Matrix validation failed, e.g., exceeding maximum size.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Unexpected Error",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<Matrix> save(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = """
                                    {
                                      "matrix": [
                                        ["A", "O", "S", "S", "O"],
                                        ["Y", "R", "Z", "X", "L"],
                                        ["J", "S", "A", "P", "M"],
                                        ["J", "K", "P", "R", "Z"],
                                        ["Y", "L", "E", "R", "A"]
                                      ]
                                    }""")
                    )
            ) @RequestBody Matrix matrix) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(matrix));
    }

    @GetMapping
    @Operation(summary = "List all matrices present in the database", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Operation succeeded and returns a list of matrices with at least one register."),
            @ApiResponse(responseCode = "204", description = "No Content -  Operation succeeded and returns an empty list beacuse no matrices were found.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Unexpected Error",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<List<Matrix>> findAll() {
        List<Matrix> matrices = service.findAll();
        return matrices.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok().body(matrices);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a matrix by ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Operation succeeded and a matrix was found and returned."),
            @ApiResponse(responseCode = "204", description = "No Content - Operation succeeded, but no matrix was found with the given ID",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Unexpected Error",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<Matrix> findById(@PathVariable UUID id) {
        return service.findById(id).map(matrix -> ResponseEntity.ok().body(matrix))
                .orElse(ResponseEntity.noContent().build());
    }

}