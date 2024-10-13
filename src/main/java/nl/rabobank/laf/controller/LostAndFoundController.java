package nl.rabobank.laf.controller;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.rabobank.laf.exception.LostAndFoundException;
import nl.rabobank.laf.model.ClaimItemsRequestDto;
import nl.rabobank.laf.model.ClaimItemsResponseDto;
import nl.rabobank.laf.model.ClaimedItemsResponseDto;
import nl.rabobank.laf.model.LostItemDto;
import nl.rabobank.laf.service.LostAndFoundService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Controller class for handling lost and found related operations.
 */
@RestController
@RequestMapping("/lost-and-found")
@AllArgsConstructor
@Slf4j
public class LostAndFoundController {

    private final LostAndFoundService lostAndFoundService;

    /**
     * Endpoint to upload and store lost data from a PDF file.
     *
     * @param lostFile the PDF file containing lost data
     * @return ResponseEntity with the status and message of the operation
     */
    @PostMapping("/upload-and-store-lost-data")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<LostItemDto>> uploadAndStoreLostData(@RequestParam("lostFile") MultipartFile lostFile) {
        // Validate the uploaded file
        if (lostFile.isEmpty() || lostFile.getContentType() == null || lostFile.getContentType().isEmpty() || !lostFile.getContentType().equals("application/pdf")) {
            log.error("Invalid PDF file uploaded. {}", lostFile.getOriginalFilename());
            throw new LostAndFoundException("Please upload a valid PDF file.");
        }
        // Store the lost data
        return ResponseEntity.status(HttpStatus.OK).body(lostAndFoundService.storeLostData(lostFile));
    }

    /**
     * Endpoint to read lost data.
     *
     * @return ResponseEntity with the list of lost items
     */
    @GetMapping("/read-lost-data")
    public ResponseEntity<List<LostItemDto>> readLostData() {
        return ResponseEntity.ok(lostAndFoundService.readLostData());
    }

    /**
     * Endpoint to claim a lost item.
     *
     * @param claimItemsRequestDto the request DTO containing claim details
     * @return ResponseEntity with the list of claimed items
     */
    @PostMapping("/claim-lost-item")
    public ResponseEntity<List<ClaimItemsResponseDto>> claimLostItem(@RequestBody ClaimItemsRequestDto claimItemsRequestDto) {
        return ResponseEntity.ok(lostAndFoundService.claimLostItem(claimItemsRequestDto));
    }

    /**
     * Endpoint to retrieve claimed items.
     *
     * @return ResponseEntity with the list of claimed items
     */
    @GetMapping("/retrieve-claimed-items")
//    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<ClaimedItemsResponseDto>> retrieveClaimedItems() {
        return ResponseEntity.ok(lostAndFoundService.retrieveClaimedItems());
    }

}