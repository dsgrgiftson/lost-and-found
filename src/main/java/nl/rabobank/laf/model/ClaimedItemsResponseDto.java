package nl.rabobank.laf.model;

import java.sql.Timestamp;

/**
 * Data Transfer Object (DTO) for representing claimed items response.
 * This record encapsulates the details of a claimed item.
 *
 * @param claimantUserId the unique identifier of the user who claimed the item
 * @param claimantFirstname the first name of the user who claimed the item
 * @param lostItemId the unique identifier of the lost item
 * @param lostItemName the name of the lost item
 * @param claimedQuantity the quantity of the lost item that was claimed
 * @param claimedTimestamp the timestamp when the item was claimed
 */
public record ClaimedItemsResponseDto(Long claimantUserId, String claimantFirstname, Long lostItemId,
                                      String lostItemName, int claimedQuantity, Timestamp claimedTimestamp) {
}