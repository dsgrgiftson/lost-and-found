package nl.rabobank.laf.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;
import nl.rabobank.laf.security.RoleEnum;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Entity class representing a role.
 * This class is mapped to the ROLES table in the database.
 */
@Entity
@Table(name = "ROLES")
@Getter
@Setter
public class Roles {

    /**
     * The unique identifier for the role.
     * This value is generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    /**
     * The name of the role.
     * This value is unique and cannot be null.
     */
    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    /**
     * The description of the role.
     * This value cannot be null.
     */
    @Column(nullable = false)
    private String description;

    /**
     * The timestamp when the role was created.
     * This value is set automatically and cannot be updated.
     */
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Timestamp createdAt;

    /**
     * The timestamp when the role was last updated.
     * This value is set automatically.
     */
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

}