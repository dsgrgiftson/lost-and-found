package nl.rabobank.laf.model;

/**
 * Data Transfer Object (DTO) for representing a lost item.
 * This class is used to transfer data between different layers of the application.
 *
 * @param lostItemId the unique identifier for the lost item
 * @param itemName the name of the lost item
 * @param quantity the quantity of the lost item
 * @param place the place where the lost item was found
 */
public record LostItemDto(Long lostItemId, String itemName, int quantity, String place) {
}