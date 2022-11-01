package car.com.Cars.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * model of exception response
 */
@Data
@AllArgsConstructor
public class ExceptionResponse {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timeStamp;
    private String message;

    public ExceptionResponse(HttpStatus status) {
        this.status = status;
        this.timeStamp = LocalDateTime.now();
    }


}
