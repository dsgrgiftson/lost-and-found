package nl.rabobank.laf.model;

/**
 * Data Transfer Object (DTO) for representing items and their claimed quantities.
 * This record encapsulates the details of an item and the quantity claimed.
 *
 * @param lostItemId the unique identifier of the lost item
 * @param claimedQuantity the quantity of the lost item that was claimed
 */
public record ItemsAndQuantity(Long lostItemId, int claimedQuantity) {
}