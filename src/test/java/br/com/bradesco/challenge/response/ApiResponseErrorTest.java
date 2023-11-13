package br.com.bradesco.challenge.response;

import br.com.bradesco.challenge.web.response.ApiResponseError;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.http.HttpStatus;

public class ApiResponseErrorTest {

    @Test
    public void testConstructorAndBuilder() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "message";

        ApiResponseError response = ApiResponseError.builder()
                .status(status)
                .message(message)
                .build();

        assertEquals(status.value(), response.getStatusCode());
        assertEquals(status, response.getStatus());
        assertEquals(message, response.getMessage());
        assertNotNull(response.getTimestamp());
    }

    @Test
    public void testToString() {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "message";

        ApiResponseError response = ApiResponseError.builder()
                .status(status)
                .message(message)
                .build();

        String expected = "ApiResponseError(statusCode=" + status.value() + ", status=" + status + ", message=" + message + ", timestamp=" + response.getTimestamp() + ")";
        assertEquals(expected, response.toString());
    }
}