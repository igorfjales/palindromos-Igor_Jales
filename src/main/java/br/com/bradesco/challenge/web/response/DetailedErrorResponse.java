package br.com.bradesco.challenge.web.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DetailedErrorResponse {
    private String error;
    private String message;
    private HttpStatus status;
    private int statusCode;
    private String timestamp;
    private String method;
    private String path;
    private String controllerName;
    private String methodName;
    private String controllerPath;
    private String exceptionClassName;
    private Map<String, String> queryParams;
    private Map<String, String> routeParams;
}
