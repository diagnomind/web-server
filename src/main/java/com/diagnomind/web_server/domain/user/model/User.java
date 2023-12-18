package com.diagnomind.web_server.domain.user.model;

import com.diagnomind.web_server.domain.hospital.model.Hospital;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a user entity with details such as name, surname, and social security number (SSN),
 * associated with a specific hospital.
 */
@Getter
@Setter
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The name of the user. Cannot be null.
     */
    @Column(nullable = false, name = "name")
    private String name;

    /**
     * The surname of the user. Cannot be null.
     */
    @Column(nullable = false, name = "surname")
    private String surname;

    /**
     * The social security number (SSN) of the user. Cannot be null.
     */
    @Column(nullable = false, name = "ssn")
    private String ssn; // Social security number

    /**
     * The hospital associated with the user.
     */
    @ManyToOne
    private Hospital hospital;

    /**
     * Updates the user information with the provided user's details.
     *
     * @param updatedUser The User object containing the modified details to be applied.
     * @return The updated User object.
     */
    public User update(User updatedUser) {
        this.name = updatedUser.getName();
        this.surname = updatedUser.getSurname();
        this.ssn = updatedUser.getSsn();
        return this;
    }
}
