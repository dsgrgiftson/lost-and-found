package nl.rabobank.laf.util;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import nl.rabobank.laf.exception.LostAndFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Utility class for handling file operations related to lost items.
 * This class provides methods to move files to an upload directory and delete files.
 */
@Component
@Slf4j
public class FileUtils {

    private File uploadDir;

    /**
     * Creates the upload directory when the component is initialized.
     * This method is called after the bean is constructed.
     */
    @PostConstruct
    public void createUploadDir() {
        // Save the file to a local directory
        File uploadDirFile = new File("uploads/");
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }
        log.info("Upload directory created: {}", uploadDirFile.getAbsolutePath());
        uploadDir = uploadDirFile;
    }

    /**
     * Deletes the upload directory when the component is destroyed.
     * This method is called before the bean is destroyed.
     */
    @PreDestroy
    public void deleteUploadDir() {
        // Delete the directory and its contents
        if (uploadDir.exists()) {
            uploadDir.delete();
        }
        log.info("Upload directory deleted: {}", uploadDir.getAbsolutePath());
    }

    /**
     * Moves the provided file to the upload directory.
     *
     * @param lostFile the file to be moved
     * @return the destination file in the upload directory
     * @throws LostAndFoundException if an error occurs while moving the file
     */
    public File moveToUploadDir(MultipartFile lostFile) {
        File dest = new File(uploadDir.getAbsolutePath(), lostFile.getOriginalFilename());
        try {
            lostFile.transferTo(dest);
        } catch (IOException e) {
            throw new LostAndFoundException("Exception occurred while trying to move file to upload directory", e);
        }
        return dest;
    }

    /**
     * Deletes the provided file from the upload directory.
     *
     * @param lostFile the file to be deleted
     * @throws LostAndFoundException if an error occurs while deleting the file
     */
    public void deleteFile(MultipartFile lostFile) {
        File dest = new File(uploadDir.getAbsolutePath(), lostFile.getOriginalFilename());
        try {
            dest.delete();
        } catch (Exception e) {
            throw new LostAndFoundException("Exception occurred while trying to delete file", e);
        }
    }
}