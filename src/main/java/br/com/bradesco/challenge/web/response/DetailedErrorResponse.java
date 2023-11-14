package br.com.bradesco.challenge.web.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@Builder
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

    @Override
    public String toString() {
        return "DetailedErrorResponse{" +
                "error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", statusCode=" + statusCode +
                ", timestamp='" + timestamp + '\'' +
                ", method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", controllerName='" + controllerName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", controllerPath='" + controllerPath + '\'' +
                ", exceptionClassName='" + exceptionClassName + '\'' +
                ", queryParams=" + queryParams +
                ", routeParams=" + routeParams +
                '}';
    }

    public String toJsonString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return super.toString();
        }
    }
}
