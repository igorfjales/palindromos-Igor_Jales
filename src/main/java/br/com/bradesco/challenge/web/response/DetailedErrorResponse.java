package br.com.bradesco.challenge.web.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@Builder
@ToString
public class DetailedErrorResponse {
    private String error;
    private String message;
    private HttpStatus status;
    private int statusCode;
    private LocalDateTime timestamp;
    private String method;
    private String path;
    private String controllerName;
    private String methodName;
    private String controllerPath;
    private String exceptionClassName;
    private Map<String, String> queryParams;
    private Map<String, String> routeParams;
}
