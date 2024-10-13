package nl.rabobank.laf.service;

import java.io.IOException;
import lombok.AllArgsConstructor;
import nl.rabobank.laf.exception.LostAndFoundException;
import nl.rabobank.laf.repository.LostItemRepository;
import nl.rabobank.laf.util.FileUtils;
import nl.rabobank.laf.util.TransformationUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service class for handling operations related to lost items.
 */
@Component
@AllArgsConstructor
public class LostService {

    private final FileUtils fileUtils;
    private final PdfFileService pdfFileService;
    private final TransformationUtil transformationUtil;
    private final LostItemRepository lostItemRepository;

    /**
     * Stores lost data from the provided PDF file.
     *
     * @param lostFile the PDF file containing lost data
     * @throws LostAndFoundException if an error occurs while processing the file
     */
    public void storeLostData(MultipartFile lostFile) {
        try {
            // Save lost items to the repository
            lostItemRepository.saveAll(
                    transformationUtil.lostItemDtoToEntity(
                            pdfFileService.getLostItems(
                                    fileUtils.moveToUploadDir(lostFile))));
        } catch (IOException e) {
            // Throw custom exception if an error occurs
            throw new LostAndFoundException("Exception occurred while trying to get lost items from PDF file and saving it", e);
        } finally {
            // Delete the uploaded file after processing
            fileUtils.deleteFile(lostFile);
        }
    }

}