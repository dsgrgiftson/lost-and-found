package nl.rabobank.laf.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a lost item.
 * This class is mapped to the LOST_ITEM table in the database.
 */
@Entity
@Table(name = "LOST_ITEM")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LostItem {

    /**
     * The unique identifier for the lost item.
     * This value is generated using a sequence.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LostItemIdSequence")
    @SequenceGenerator(name = "LostItemIdSequence", sequenceName = "LOST_ITEM_ID_SEQUENCE", allocationSize = 1)
    @Column(name = "LOST_ITEM_ID")
    private Long lostItemId;

    /**
     * The name of the lost item.
     */
    @Column(name = "ITEM_NAME")
    private String itemName;

    /**
     * The quantity of the lost item.
     */
    @Column(name = "QUANTITY")
    private Integer quantity;

    /**
     * The place where the lost item was found.
     */
    @Column(name = "PLACE")
    private String place;

}