package br.com.bradesco.challenge.controller;

import br.com.bradesco.challenge.domain.model.Matrix;
import br.com.bradesco.challenge.domain.model.Palindrome;
import br.com.bradesco.challenge.domain.service.IPalindromeService;
import br.com.bradesco.challenge.web.controller.PalindromeController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PalindromeControllerTest {

    @Mock
    private IPalindromeService service;

    @InjectMocks
    private PalindromeController controller;

    @BeforeEach
    void setUp() {
        service = mock(IPalindromeService.class);
        controller = new PalindromeController(service);
    }

    @Test
    public void testFindById() {
        UUID id = UUID.randomUUID();

        Palindrome palindrome = Palindrome.builder()
                .id(id)
                .palindrome("12321")
                .build();

        when(service.findById(any(UUID.class))).thenReturn(Optional.of(palindrome));

        ResponseEntity<Palindrome> response = controller.findById(id);

        Assertions.assertNotNull(response);
        Palindrome responseBody = response.getBody();
        Assertions.assertNotNull(responseBody);
        assertThat(responseBody).isEqualTo(palindrome);
    }

    @Test
    public void testFindAll() {
        List<Palindrome> palindromes = Arrays.asList(
                Palindrome.builder()
                        .palindrome("12321")
                        .matrix(Matrix.builder().matrix(new char[10][10]).build())
                        .build(),

                Palindrome.builder()
                        .palindrome("12321")
                        .matrix(Matrix.builder().matrix(new char[10][10]).build())
                        .build()
        );

        when(service.findAll()).thenReturn(palindromes);

        ResponseEntity<List<Palindrome>> response = controller.findAll();

        Assertions.assertNotNull(response);
        List<Palindrome> responseBody = response.getBody();
        Assertions.assertNotNull(responseBody);
        assertThat(responseBody).isEqualTo(palindromes);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFindByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(service.findById(any(UUID.class))).thenReturn(Optional.empty());

        ResponseEntity<Palindrome> response = controller.findById(id);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }
}
