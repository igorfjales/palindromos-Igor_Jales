package br.com.bradesco.challenge;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Palindrome Hunt Challenge Api", version = "1.0.0", description = "Api that hunt palindromes in matrices of characters"))
public class PalindromeHuntChallengeApplication {
    public static void main(String[] args) {
        SpringApplication.run(PalindromeHuntChallengeApplication.class, args);
    }
}
