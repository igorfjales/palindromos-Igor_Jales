package br.com.bradesco.challenge.exception;

import br.com.bradesco.challenge.web.response.ApiResponseError;
import br.com.bradesco.challenge.web.response.DetailedErrorResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MatrixValidationException.class)
    public ResponseEntity<ApiResponseError> badRequestExceptionHandler(MatrixValidationException exception, HandlerMethod handlerMethod, HttpServletRequest request) {
        DetailedErrorResponse detailedErrorResponse = createDetailedErrorResponse(exception, handlerMethod, request, HttpStatus.BAD_REQUEST);
        ApiResponseError apiResponseError = createApiResponseError(detailedErrorResponse);
        log.error(detailedErrorResponse.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponseError);
    }

    @ExceptionHandler(value = {
            Exception.class
    })
    public ResponseEntity<ApiResponseError> globalExceptionHandler(Exception exception, HandlerMethod handlerMethod, HttpServletRequest request) {
        DetailedErrorResponse detailedErrorResponse = createDetailedErrorResponse(exception, handlerMethod, request, HttpStatus.INTERNAL_SERVER_ERROR);
        ApiResponseError apiResponseError = createApiResponseError(detailedErrorResponse);
        log.error(detailedErrorResponse.toString());
        apiResponseError.setMessage("Unexpected error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponseError);
    }

    private ApiResponseError createApiResponseError(DetailedErrorResponse detailedErrorResponse) {
        return ApiResponseError.builder()
                .status(detailedErrorResponse.getStatus())
                .message(detailedErrorResponse.getMessage())
                .timestamp(detailedErrorResponse.getTimestamp())
                .build();
    }

    private DetailedErrorResponse createDetailedErrorResponse(Exception exception, HandlerMethod handlerMethod, HttpServletRequest request, HttpStatus httpStatus) {
        String controllerPath = handlerMethod.getMethod().getDeclaringClass().toString();
        String controllerName = handlerMethod.getMethod().getDeclaringClass().getSimpleName();
        String methodName = handlerMethod.getMethod().getName();
        String timestamp = Instant.now().toString();
        String message = exception.getMessage();
        String exceptionClassName = exception.getClass().getName();
        Map<String, String> queryParams = extractQueryParams(request);
        Map<String, String> routeParams = extractRouteParams(request);

        return DetailedErrorResponse.builder()
                .status(httpStatus)
                .statusCode(httpStatus.value())
                .error(httpStatus.getReasonPhrase())
                .method(request.getMethod())
                .path(request.getRequestURL().toString())
                .controllerPath(controllerPath)
                .controllerName(controllerName)
                .methodName(methodName)
                .timestamp(timestamp)
                .message(message)
                .exceptionClassName(exceptionClassName)
                .queryParams(queryParams)
                .routeParams(routeParams)
                .build();
    }

    private Map<String, String> extractQueryParams(HttpServletRequest request) {
        Map<String, String> queryParams = new HashMap<>();
        request.getParameterMap().forEach((key, value) -> queryParams.put(key, Arrays.toString(value)));
        return queryParams;
    }

    private Map<String, String> extractRouteParams(HttpServletRequest request) {
        Map<String, String> routeParams = new HashMap<>();
        Object pathVariablesObj = request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        if (pathVariablesObj instanceof Map<?, ?> pathVariables) {

            for (Map.Entry<?, ?> entry : pathVariables.entrySet()) {

                if (entry.getKey() instanceof String && entry.getValue() instanceof String) {
                    routeParams.put((String) entry.getKey(), (String) entry.getValue());
                }
            }
        }

        return routeParams;
    }
}
