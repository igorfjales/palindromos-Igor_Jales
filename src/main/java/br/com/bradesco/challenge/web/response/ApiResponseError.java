package br.com.bradesco.challenge.web.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseError {
    private int statusCode;
    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;

    @SuppressWarnings("unused")
    public static class ApiResponseErrorBuilder {
        public ApiResponseError build() {
            this.statusCode = this.status.value();
            this.timestamp = LocalDateTime.now();
            return new ApiResponseError(this.statusCode, this.status, this.message, this.timestamp);
        }
    }
}
