package nl.rabobank.laf.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.rabobank.laf.service.LostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

    private final LostService lostService;

    /**
     * Endpoint to upload and store lost data from a PDF file.
     *
     * @param lostFile the PDF file containing lost data
     * @return ResponseEntity with the status and message of the operation
     */
    @PostMapping("/upload-and-store-lost-data")
    public ResponseEntity<String> uploadAndStoreLostData(@RequestParam("lostFile") MultipartFile lostFile) {
        // Validate the uploaded file
        if (lostFile.isEmpty() || lostFile.getContentType() == null || lostFile.getContentType().isEmpty() || !lostFile.getContentType().equals("application/pdf")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file. Please upload a PDF file.");
        }
        // Store the lost data
        lostService.storeLostData(lostFile);
        return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully: " + lostFile.getOriginalFilename());
    }

}