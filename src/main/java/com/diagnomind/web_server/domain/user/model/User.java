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
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "surname")
    private String surname;

    @Column(nullable = false, name = "ssn")
    private String ssn; // Social security number

    @ManyToOne
    private Hospital hospital;

    public User update(User updatedUser) {
        this.name = updatedUser.getName();
        this.surname = updatedUser.getSurname();
        this.ssn = updatedUser.getSsn();
        return this;
    }
}
