package nl.rabobank.laf.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import nl.rabobank.laf.model.LostItemDto;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

/**
 * Service class for handling operations related to PDF files.
 */
@Component
@Slf4j
public class PdfFileService {

    /**
     * Extracts lost items from the provided PDF file.
     *
     * @param pdfFile the PDF file to extract lost items from
     * @return a list of LostItemDto objects containing the extracted lost items
     * @throws IOException if an error occurs while reading the PDF file
     */
    public List<LostItemDto> getLostItems(File pdfFile) throws IOException {
        List<LostItemDto> lostItemDtos = new ArrayList<>();

        // Load the PDF document
        try (PDDocument document = Loader.loadPDF(pdfFile)) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            String text = pdfStripper.getText(document);

            // Split the text into lines
            String[] lines = text.split("\n");
            String itemName = null;
            int quantity = 0;
            String place = null;

            // Parse each line to extract lost item details
            for (String line : lines) {
                line = line.trim();
                if (line.startsWith("ItemName:")) {
                    itemName = line.substring(9).trim();
                } else if (line.startsWith("Quantity:")) {
                    quantity = Integer.parseInt(line.substring(9).trim());
                } else if (line.startsWith("Place:")) {
                    place = line.substring(6).trim();
                } else if (line.startsWith("-")) {
                    // Add the extracted lost item to the list
                    if (itemName != null && quantity > 0 && place != null) {
                        lostItemDtos.add(new LostItemDto(null, itemName, quantity, place));
                        itemName = null;
                        quantity = 0;
                        place = null;
                    } else {
                        log.warn("Invalid data in PDF file: itemName={}, quantity={}, place={}", itemName, quantity, place);
                    }
                }
            }
        }
        return lostItemDtos;
    }
}