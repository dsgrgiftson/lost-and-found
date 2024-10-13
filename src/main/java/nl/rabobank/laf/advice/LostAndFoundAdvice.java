package nl.rabobank.laf.advice;

import lombok.extern.slf4j.Slf4j;
import nl.rabobank.laf.exception.LostAndFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the Lost and Found application.
 * This class handles exceptions thrown by controllers and provides appropriate HTTP responses.
 */
@ControllerAdvice
@Slf4j
public class LostAndFoundAdvice {

    /**
     * Handles LostAndFoundException and returns a BAD_REQUEST response.
     *
     * @param ex the LostAndFoundException thrown by the application
     * @return ResponseEntity with the error message and BAD_REQUEST status
     */
    @ExceptionHandler(LostAndFoundException.class)
    public ResponseEntity<String> handleLostAndFoundException(LostAndFoundException ex) {
        log.error("LostAndFoundException occurred: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Handles general exceptions and returns an INTERNAL_SERVER_ERROR response.
     *
     * @param ex the Exception thrown by the application
     * @return ResponseEntity with the error message and INTERNAL_SERVER_ERROR status
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        log.error("An unexpected error occurred: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + ex.getMessage());
    }
}