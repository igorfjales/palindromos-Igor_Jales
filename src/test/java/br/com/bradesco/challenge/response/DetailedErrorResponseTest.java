package br.com.bradesco.challenge.response;

import br.com.bradesco.challenge.web.response.DetailedErrorResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class DetailedErrorResponseTest {
    @Test
    public void testConstructorAndBuilder() {
        String error = "error";
        String message = "message";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        int statusCode = 400;
        String timestamp = "timestamp";
        String method = "method";
        String path = "path";
        String controllerName = "controllerName";
        String methodName = "methodName";
        String controllerPath = "controllerPath";
        String exceptionClassName = "exceptionClassName";
        Map<String, String> queryParams = Map.of("key", "value");
        Map<String, String> routeParams = Map.of("key", "value");
        DetailedErrorResponse response = DetailedErrorResponse.builder()
                .error(error)
                .message(message)
                .status(status)
                .statusCode(statusCode)
                .timestamp(timestamp)
                .method(method)
                .path(path)
                .controllerName(controllerName)
                .methodName(methodName)
                .controllerPath(controllerPath)
                .exceptionClassName(exceptionClassName)
                .queryParams(queryParams)
                .routeParams(routeParams)
                .build();
        assertEquals(error, response.getError());
        assertEquals(message, response.getMessage());
        assertEquals(status, response.getStatus());
        assertEquals(statusCode, response.getStatusCode());
        assertEquals(timestamp, response.getTimestamp());
        assertEquals(method, response.getMethod());
        assertEquals(path, response.getPath());
        assertEquals(controllerName, response.getControllerName());
        assertEquals(methodName, response.getMethodName());
        assertEquals(controllerPath, response.getControllerPath());
        assertEquals(exceptionClassName, response.getExceptionClassName());
        assertEquals(queryParams, response.getQueryParams());
        assertEquals(routeParams, response.getRouteParams());
    }

    @Test
    public void testToString() {
        String error = "error";
        String message = "message";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        int statusCode = HttpStatus.BAD_REQUEST.value();
        String timestamp = "timestamp";
        String method = "method";
        String path = "path";
        String controllerName = "controllerName";
        String methodName = "methodName";
        String controllerPath = "controllerPath";
        String exceptionClassName = "exceptionClassName";
        Map<String, String> queryParams = Map.of("key", "value");
        Map<String, String> routeParams = Map.of("key", "value");
        DetailedErrorResponse response = DetailedErrorResponse.builder()
                .error(error)
                .message(message)
                .status(status)
                .statusCode(statusCode)
                .timestamp(timestamp)
                .method(method)
                .path(path)
                .controllerName(controllerName)
                .methodName(methodName)
                .controllerPath(controllerPath)
                .exceptionClassName(exceptionClassName)
                .queryParams(queryParams)
                .routeParams(routeParams)
                .build();

        String responseString = response.toString();

        assertTrue(responseString.contains("error='" + error + "'"));
        assertTrue(responseString.contains("message='" + message + "'"));
        assertTrue(responseString.contains("status=" + status));
        assertTrue(responseString.contains("statusCode=" + statusCode));
        assertTrue(responseString.contains("timestamp='" + timestamp + "'"));
        // Adicione verificações semelhantes para os outros campos

        // Você ainda pode verificar a estrutura geral se desejar
        assertTrue(responseString.startsWith("DetailedErrorResponse{"));
        assertTrue(responseString.endsWith("}"));
    }

    @Test
    public void testToJsonString() {
        String error = "error";
        String message = "message";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        int statusCode = HttpStatus.BAD_REQUEST.value();
        String timestamp = "timestamp";
        String method = "method";
        String path = "path";
        String controllerName = "controllerName";
        String methodName = "methodName";
        String controllerPath = "controllerPath";
        String exceptionClassName = "exceptionClassName";
        Map<String, String> queryParams = Map.of("key", "value");
        Map<String, String> routeParams = Map.of("key", "value");
        DetailedErrorResponse response = DetailedErrorResponse.builder()
                .error(error)
                .message(message)
                .status(status)
                .statusCode(statusCode)
                .timestamp(timestamp)
                .method(method)
                .path(path)
                .controllerName(controllerName)
                .methodName(methodName)
                .controllerPath(controllerPath)
                .exceptionClassName(exceptionClassName)
                .queryParams(queryParams)
                .routeParams(routeParams)
                .build();

        String jsonString = response.toJsonString();

        // Use uma biblioteca de assertiva JSON ou análise manual, dependendo da preferência
        assertEquals(
                "{\"error\":\"error\",\"message\":\"message\",\"status\":\"BAD_REQUEST\",\"statusCode\":400,\"timestamp\":\"timestamp\",\"method\":\"method\",\"path\":\"path\",\"controllerName\":\"controllerName\",\"methodName\":\"methodName\",\"controllerPath\":\"controllerPath\",\"exceptionClassName\":\"exceptionClassName\",\"queryParams\":{\"key\":\"value\"},\"routeParams\":{\"key\":\"value\"}}",
                jsonString
        );
    }

    @Test
    public void testSetters() {
        String error = "error";
        String message = "message";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        int statusCode = 400;
        String timestamp = "timestamp";
        String method = "method";
        String path = "path";
        String controllerName = "controllerName";
        String methodName = "methodName";
        String controllerPath = "controllerPath";
        String exceptionClassName = "exceptionClassName";
        Map<String, String> queryParams = Map.of("key", "value");
        Map<String, String> routeParams = Map.of("key", "value");

        DetailedErrorResponse response = new DetailedErrorResponse();
        response.setError(error);
        response.setMessage(message);
        response.setStatus(status);
        response.setStatusCode(statusCode);
        response.setTimestamp(timestamp);
        response.setMethod(method);
        response.setPath(path);
        response.setControllerName(controllerName);
        response.setMethodName(methodName);
        response.setControllerPath(controllerPath);
        response.setExceptionClassName(exceptionClassName);
        response.setQueryParams(queryParams);
        response.setRouteParams(routeParams);

        assertEquals(error, response.getError());
        assertEquals(message, response.getMessage());
        assertEquals(status, response.getStatus());
        assertEquals(statusCode, response.getStatusCode());
        assertEquals(timestamp, response.getTimestamp());
        assertEquals(method, response.getMethod());
        assertEquals(path, response.getPath());
        assertEquals(controllerName, response.getControllerName());
        assertEquals(methodName, response.getMethodName());
        assertEquals(controllerPath, response.getControllerPath());
        assertEquals(exceptionClassName, response.getExceptionClassName());
        assertEquals(queryParams, response.getQueryParams());
        assertEquals(routeParams, response.getRouteParams());
    }
}