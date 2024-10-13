package nl.rabobank.laf.repository;

import java.util.List;
import nl.rabobank.laf.entity.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for LostItem entities.
 * This interface provides CRUD operations for LostItem entities by extending JpaRepository.
 */
@Repository
public interface LostItemRepository extends JpaRepository<LostItem, Long> {

    /**
     * Finds a list of LostItem entities by their unique identifiers.
     *
     * @param lostItemIds the list of unique identifiers for the lost items
     * @return a list of LostItem entities matching the provided identifiers
     */
    public List<LostItem> findByLostItemIdIn(List<Long> lostItemIds);
}