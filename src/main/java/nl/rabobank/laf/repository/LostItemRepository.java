package nl.rabobank.laf.repository;

import nl.rabobank.laf.entity.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for LostItem entities.
 * This interface provides CRUD operations for LostItem entities by extending JpaRepository.
 */
@Repository
public interface LostItemRepository extends JpaRepository<LostItem, Long> {
}