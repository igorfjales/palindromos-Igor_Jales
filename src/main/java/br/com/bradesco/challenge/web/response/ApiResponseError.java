package br.com.bradesco.challenge.web.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseError {
    private Integer statusCode;
    private HttpStatus status;
    private String message;
    private String timestamp;

    @SuppressWarnings("unused")
    public static class ApiResponseErrorBuilder {
        public ApiResponseError build() {
            this.statusCode = status != null ? this.status.value() : null;
            this.timestamp = Instant.now().toString();
            return new ApiResponseError(this.statusCode, this.status, this.message, this.timestamp);
        }
    }
}
