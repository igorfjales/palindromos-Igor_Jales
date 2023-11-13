package br.com.bradesco.challenge.web.controller;

import br.com.bradesco.challenge.domain.model.Palindrome;
import br.com.bradesco.challenge.domain.service.IPalindromeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/palindrome", produces = {"application/json"})
@Tag(name = "Palindrome")
public class PalindromeController {

    private final IPalindromeService service;

    public PalindromeController(IPalindromeService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List all palindromes present in the database", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Operation succeeded and returns a list of palindromes with at least one register.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(name = "example", value = "[{\"id\":\"16029545-d4f3-4c43-ba87-9ea894aaeed2\",\"palindrome\":\"OSSO\"}]"))),
            @ApiResponse(responseCode = "204", description = "No Content -  Operation succeeded and returns an empty list beacuse no palindromes were found.",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Unexpected Error",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<List<Palindrome>> findAll() {
        List<Palindrome> palindrome = service.findAll();
        return palindrome.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok().body(palindrome);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find a palindrome by ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok - Operation succeeded and a palindrome was found and returned.",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(name = "example", value = "{\"id\":\"16029545-d4f3-4c43-ba87-9ea894aaeed2\",\"palindrome\":\"OSSO\"}"))),
            @ApiResponse(responseCode = "204", description = "No Content - Operation succeeded, but no palindrome was found with the given ID",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Unexpected Error",
                    content = @Content(mediaType = "application/json")),
    })
    public ResponseEntity<Palindrome> findById(@PathVariable UUID id) {
        return service.findById(id)
                .map(palindrome -> ResponseEntity.ok().body(palindrome))
                .orElse(ResponseEntity.noContent().build());
    }

}