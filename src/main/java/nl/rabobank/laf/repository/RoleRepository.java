package nl.rabobank.laf.repository;

import java.util.Optional;
import nl.rabobank.laf.entity.Roles;
import nl.rabobank.laf.security.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Roles entities.
 * This interface extends JpaRepository to provide CRUD operations for Roles entities.
 */
@Repository
public interface RoleRepository extends JpaRepository<Roles, Integer> {

    /**
     * Finds a Role entity by its name.
     *
     * @param name the name of the role
     * @return an Optional containing the Role entity if found, or empty if not found
     */
    Optional<Roles> findByName(RoleEnum name);
}