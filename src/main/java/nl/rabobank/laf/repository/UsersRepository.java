package nl.rabobank.laf.repository;

import nl.rabobank.laf.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Users entities.
 * This interface extends JpaRepository to provide CRUD operations for Users entities.
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}