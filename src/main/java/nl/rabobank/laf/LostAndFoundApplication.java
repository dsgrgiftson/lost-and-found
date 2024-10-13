package nl.rabobank.laf;

/**
 * Main application class for the Lost and Found application.
 * This class is responsible for bootstrapping the Spring Boot application.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LostAndFoundApplication {

    /**
     * Main method which serves as the entry point for the Spring Boot application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(LostAndFoundApplication.class, args);
    }

}