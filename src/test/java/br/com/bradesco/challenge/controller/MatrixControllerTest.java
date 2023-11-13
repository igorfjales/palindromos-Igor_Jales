package br.com.bradesco.challenge.controller;

import br.com.bradesco.challenge.exception.GlobalExceptionHandler;
import br.com.bradesco.challenge.web.controller.MatrixController;
import br.com.bradesco.challenge.web.response.ApiResponseError;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import br.com.bradesco.challenge.domain.model.Matrix;
import br.com.bradesco.challenge.domain.service.IMatrixService;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.Optional;
import java.util.List;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class MatrixControllerTest {

    @Mock
    private IMatrixService service;

    @InjectMocks
    private MatrixController controller;

    @BeforeEach
    void setUp() {
        service = mock(IMatrixService.class);
        controller = new MatrixController(service);
    }

    @Test
    public void testSave() {
        Matrix matrix = Matrix.builder()
                .matrix(new char[10][10])
                .build();

        when(service.save(any(Matrix.class))).thenReturn(matrix);

        ResponseEntity<Matrix> response = controller.save(matrix);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(matrix);
    }

    @Test
    public void testFindById() {
        UUID id = UUID.randomUUID();

        Matrix matrix = Matrix.builder()
                .id(id)
                .matrix(new char[10][10])
                .build();

        when(service.findById(any(UUID.class))).thenReturn(Optional.of(matrix));

        ResponseEntity<Matrix> response = controller.findById(id);

        Assertions.assertNotNull(response);
        Matrix responseBody = response.getBody();
        Assertions.assertNotNull(responseBody);
        assertThat(responseBody).isEqualTo(matrix);
    }

    @Test
    public void testFindAll() {
        List<Matrix> matrices = Arrays.asList(
                Matrix.builder().matrix(new char[10][10]).build(),
                Matrix.builder().matrix(new char[10][10]).build()
        );

        when(service.findAll()).thenReturn(matrices);

        ResponseEntity<List<Matrix>> response = controller.findAll();

        Assertions.assertNotNull(response);
        List<Matrix> responseBody = response.getBody();
        Assertions.assertNotNull(responseBody);
        assertThat(responseBody).isEqualTo(matrices);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testFindByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(service.findById(any(UUID.class))).thenReturn(Optional.empty());

        ResponseEntity<Matrix> response = controller.findById(id);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void testSaveServiceThrowsException() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        Matrix matrix = Matrix.builder()
                .matrix(new char[10][10])
                .build();

        when(service.save(any(Matrix.class))).thenThrow(new RuntimeException("Unexpected error"));

        ApiResponseError expectedResponse = ApiResponseError.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("Unexpected error")
                .timestamp(Instant.now().toString())
                .build();

        mockMvc.perform(post("/matrix")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(matrix)))
                .andExpect(result -> verifyResponse(expectedResponse, result));
    }

    private void verifyResponse(ApiResponseError expected, MvcResult result) throws IOException {
        String responseContent = result.getResponse().getContentAsString();
        ApiResponseError actual = new ObjectMapper().readValue(responseContent, ApiResponseError.class);

        assertEquals(expected.getStatusCode(), actual.getStatusCode());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getMessage(), actual.getMessage());

        Instant expectedTimestamp = Instant.parse(expected.getTimestamp());
        Instant actualTimestamp = Instant.parse(actual.getTimestamp());

        long acceptableDifferenceInSeconds = 5;
        long timeDifferenceInSeconds = Math.abs(Duration.between(expectedTimestamp, actualTimestamp).getSeconds());
        Assertions.assertTrue(timeDifferenceInSeconds <= acceptableDifferenceInSeconds);
    }

}
