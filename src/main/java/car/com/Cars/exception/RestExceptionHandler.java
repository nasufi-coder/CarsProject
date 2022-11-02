package car.com.Cars.exception;

import car.com.Cars.exception.CustomExeption.CarNotFoundException;
import car.com.Cars.exception.CustomExeption.DeleteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.NoSuchElementException;

/**
 * handling all rest exeptions
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * @param request   of the requested endpoint
     * @param exception that is being handled
     * @return ExceptionResponse
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(HttpServletRequest request, NoSuchElementException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND);
        exceptionResponse.setMessage("The value you requested is not found! " + request.getRequestURI());
        return buildResponseEntity(exceptionResponse);
    }

    /**
     * @param request   of the requested endpoint
     * @param exception that is being handled
     * @return ExceptionResponse
     */
    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<Object> handleCarNotFoundException(HttpServletRequest request, CarNotFoundException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NO_CONTENT);
        exceptionResponse.setMessage(exception.getMessage() + " " + request.getRequestURI());
        return buildResponseEntity(exceptionResponse);
    }

    /**
     * @param request   of the requested endpoint
     * @param exception that is being handled
     * @return ExceptionResponse
     */
    @ExceptionHandler(DeleteException.class)
    public ResponseEntity<Object> handleDeleteException(HttpServletRequest request, DeleteException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST);
        exceptionResponse.setMessage(exception.getMessage() + " " + request.getRequestURI());
        return buildResponseEntity(exceptionResponse);
    }

    private ResponseEntity<Object> buildResponseEntity(ExceptionResponse exceptionResponse) {
        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
    }
}
