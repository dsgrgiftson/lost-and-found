package nl.rabobank.laf.model;

import java.sql.Timestamp;

/**
 * Data Transfer Object (DTO) for representing claimed items response.
 * This record encapsulates the details of a claimed item.
 *
 * @param lostItemId the unique identifier of the lost item
 * @param quantityClaimed the quantity of the lost item that was claimed
 * @param isClaimSubmitted the status indicating whether the claim has been submitted
 * @param claimTimestamp the timestamp when the item was claimed
 */
public record ClaimItemsResponseDto(Long lostItemId, int quantityClaimed, boolean isClaimSubmitted, Timestamp claimTimestamp) {
}