package nl.rabobank.laf.controller;

import java.sql.Timestamp;
import nl.rabobank.laf.exception.LostAndFoundException;
import nl.rabobank.laf.model.ClaimItemsRequestDto;
import nl.rabobank.laf.model.ClaimItemsResponseDto;
import nl.rabobank.laf.model.ClaimedItemsResponseDto;
import nl.rabobank.laf.model.ItemsAndQuantity;
import nl.rabobank.laf.model.LostItemDto;
import nl.rabobank.laf.service.LostAndFoundService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class LostAndFoundControllerTest {

    @Mock
    private LostAndFoundService lostAndFoundService;

    @InjectMocks
    private LostAndFoundController lostAndFoundController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void uploadAndStoreLostData_ShouldReturnOk() {
        // Arrange
        MultipartFile mockFile = org.mockito.Mockito.mock(MultipartFile.class);
        when(mockFile.isEmpty()).thenReturn(false);
        when(mockFile.getContentType()).thenReturn("application/pdf");
        List<LostItemDto> lostItemDtos = Collections.singletonList(new LostItemDto(1L, "Test Item", 1, "Test Place"));
        when(lostAndFoundService.storeLostData(any(MultipartFile.class))).thenReturn(lostItemDtos);

        // Act
        ResponseEntity<List<LostItemDto>> response = lostAndFoundController.uploadAndStoreLostData(mockFile);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lostItemDtos, response.getBody());
    }

    @Test
    void uploadAndStoreLostData_ShouldThrowException_WhenFileIsInvalid() {
        // Arrange
        MultipartFile mockFile = org.mockito.Mockito.mock(MultipartFile.class);
        when(mockFile.isEmpty()).thenReturn(true);

        // Act & Assert
        try {
            lostAndFoundController.uploadAndStoreLostData(mockFile);
        } catch (LostAndFoundException ex) {
            assertEquals("Please upload a valid PDF file.", ex.getMessage());
        }
    }

    @Test
    void readLostData_ShouldReturnOk() {
        // Arrange
        List<LostItemDto> lostItemDtos = Collections.singletonList(new LostItemDto(1L, "Test Item", 1, "Test Place"));
        when(lostAndFoundService.readLostData()).thenReturn(lostItemDtos);

        // Act
        ResponseEntity<List<LostItemDto>> response = lostAndFoundController.readLostData();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(lostItemDtos, response.getBody());
    }

    @Test
    void claimLostItem_ShouldReturnOk() {
        // Arrange
        ClaimItemsRequestDto requestDto = new ClaimItemsRequestDto(1L, List.of(new ItemsAndQuantity(1L, 1)));
        List<ClaimItemsResponseDto> responseDtos = Collections.singletonList(new ClaimItemsResponseDto(1L, 1, false, new Timestamp(System.currentTimeMillis())));
        when(lostAndFoundService.claimLostItem(any(ClaimItemsRequestDto.class))).thenReturn(responseDtos);

        // Act
        ResponseEntity<List<ClaimItemsResponseDto>> response = lostAndFoundController.claimLostItem(requestDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDtos, response.getBody());
    }

    @Test
    void retrieveClaimedItems_ShouldReturnOk() {
        // Arrange
        List<ClaimedItemsResponseDto> responseDtos = Collections.singletonList(new ClaimedItemsResponseDto(1L, "Test Firstname", 1L, "Test Item", 1, new Timestamp(System.currentTimeMillis())));
        when(lostAndFoundService.retrieveClaimedItems()).thenReturn(responseDtos);

        // Act
        ResponseEntity<List<ClaimedItemsResponseDto>> response = lostAndFoundController.retrieveClaimedItems();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDtos, response.getBody());
    }
}