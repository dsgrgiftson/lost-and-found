package nl.rabobank.laf.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a claim for a lost item.
 * This class is mapped to the LOST_ITEM_CLAIM table in the database.
 */
@Entity
@Table(name = "LOST_ITEM_CLAIM")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LostItemClaim {

    /**
     * The unique identifier for the lost item claim.
     * This value is generated using a sequence.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ClaimIdSequence")
    @SequenceGenerator(name = "ClaimIdSequence", sequenceName = "CLAIM_ID_SEQUENCE", allocationSize = 1)
    @Column(name = "CLAIM_ID")
    private Long claimId;

    /**
     * The lost item associated with this claim.
     */
    @JoinColumn(name = "LOST_ITEM_ID")
    @ManyToOne(targetEntity = LostItem.class)
    private LostItem lostItem;

    /**
     * The quantity of the lost item claimed.
     */
    @Column(name = "CLAIMED_QUANTITY")
    private int claimedQuantity;

    /**
     * The user who made the claim.
     */
    @JoinColumn(name = "USER_ID")
    @ManyToOne(targetEntity = Users.class)
    private Users user;

    /**
     * The timestamp when the claim was made.
     * This value is set to the current system time by default.
     */
    @Column(name = "CLAIM_TIMESTAMP")
    private Timestamp claimTimestamp = new Timestamp(System.currentTimeMillis());

}