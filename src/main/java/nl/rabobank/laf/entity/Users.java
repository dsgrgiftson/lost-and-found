package nl.rabobank.laf.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a user.
 * This class is mapped to the USERS table in the database.
 */
@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users {

    /**
     * The unique identifier for the user.
     * This value is generated using a sequence.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserIdSequence")
    @SequenceGenerator(name = "UserIdSequence", sequenceName = "USER_ID_SEQUENCE", allocationSize = 1)
    @Column(name = "USER_ID")
    private Long userId;

    /**
     * The first name of the user.
     */
    @Column(name = "FIRSTNAME")
    private String firstName;

    /**
     * The last name of the user.
     */
    @Column(name = "LASTNAME")
    private String lastName;

    /**
     * The email address of the user.
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * The password of the user.
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * The role associated with the user.
     * This is a many-to-one relationship with the Roles entity.
     */
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Roles roles;

}