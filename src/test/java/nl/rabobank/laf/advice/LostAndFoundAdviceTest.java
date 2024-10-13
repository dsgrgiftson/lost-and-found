package nl.rabobank.laf.advice;

import nl.rabobank.laf.exception.LostAndFoundException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class LostAndFoundAdviceTest {

    private LostAndFoundAdvice lostAndFoundAdvice;

    @BeforeEach
    void setUp() {
        lostAndFoundAdvice = new LostAndFoundAdvice();
    }

    @Test
    void handleLostAndFoundException_ShouldReturnBadRequest() {
        // Arrange
        LostAndFoundException exception = new LostAndFoundException("Lost and found error");

        // Act
        ResponseEntity<String> response = lostAndFoundAdvice.handleLostAndFoundException(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Lost and found error", response.getBody());
    }

    @Test
    void handleGeneralException_ShouldReturnInternalServerError() {
        // Arrange
        Exception exception = new Exception("General error");

        // Act
        ResponseEntity<String> response = lostAndFoundAdvice.handleGeneralException(exception);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred: General error", response.getBody());
    }
}