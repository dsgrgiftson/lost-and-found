package nl.rabobank.laf.security;

/**
 * Enumeration representing the different roles available in the application.
 * This enum is used to define the roles that can be assigned to users.
 */
public enum RoleEnum {
    /**
     * Role for regular users.
     */
    USER,

    /**
     * Role for administrators with elevated privileges.
     */
    ADMIN,
}