package in.mindcraft.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@RestControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger logger =
	        LoggerFactory.getLogger(
	                GlobalExceptionHandler.class
	        );
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFoundException(
            StudentNotFoundException exception) {
        logger.error(
                "Student exception occurred: {}",
                exception.getMessage()
        );
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                exception.getMessage()
        );

        return new ResponseEntity<>(
                errorResponse,
                HttpStatus.NOT_FOUND
        );
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(
            MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return new ResponseEntity<>(
                errors,
                HttpStatus.BAD_REQUEST
        );
    }
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatchException(
            MethodArgumentTypeMismatchException exception) {

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Invalid value: " + exception.getValue()
        );

        return new ResponseEntity<>(
                errorResponse,
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception exception) {

        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                exception.getMessage()
        );

        return new ResponseEntity<>(
                errorResponse,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
    
    @ExceptionHandler(
            org.springframework.dao.IncorrectResultSizeDataAccessException.class
    )
    public ResponseEntity<ErrorResponse> handleDuplicateResult(

            org.springframework.dao.IncorrectResultSizeDataAccessException ex

    ) {

        ErrorResponse errorResponse = new ErrorResponse(

                LocalDateTime.now(),

                HttpStatus.CONFLICT.value(),

                "Duplicate data found",

                ex.getMessage()

        );

        return new ResponseEntity<>(

                errorResponse,

                HttpStatus.CONFLICT

        );

    }
    
    
 }
    
    
    
