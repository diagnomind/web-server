package com.diagnomind.web_server.domain.user.model;

import java.util.List;

import com.diagnomind.web_server.domain.hospital.model.Hospital;
import com.diagnomind.web_server.domain.request.model.Request;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @Column(name = "uid")
    private Long uid;

    @ManyToOne
    private Hospital hospital;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "surname")
    private String surname;

    @Column(nullable = false, name = "ssn")
    private String ssn;  // Social security number

    @OneToMany(mappedBy = "id")
    private List<Request> requests;

    public User update(User updatedUser) {
        this.name = updatedUser.getName();
        this.surname = updatedUser.getSurname();
        this.ssn = updatedUser.getSsn();
        return this;
    }

}
