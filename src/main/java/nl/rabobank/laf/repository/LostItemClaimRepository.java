package nl.rabobank.laf.repository;

import nl.rabobank.laf.entity.LostItemClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing LostItemClaim entities.
 * This interface extends JpaRepository to provide CRUD operations for LostItemClaim entities.
 */
@Repository
public interface LostItemClaimRepository extends JpaRepository<LostItemClaim, Long> {
}