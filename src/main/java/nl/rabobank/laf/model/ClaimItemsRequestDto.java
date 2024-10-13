package nl.rabobank.laf.model;

import java.util.List;

/**
 * Data Transfer Object (DTO) for representing a request to claim items.
 * This record encapsulates the details of the claim request.
 *
 * @param claimantUserId the unique identifier of the user making the claim
 * @param itemsAndQuantity the list of items and their respective quantities being claimed
 */
public record ClaimItemsRequestDto(Long claimantUserId, List<ItemsAndQuantity> itemsAndQuantity) {
}