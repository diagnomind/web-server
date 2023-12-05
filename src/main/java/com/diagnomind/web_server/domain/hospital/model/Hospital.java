package com.diagnomind.web_server.domain.hospital.model;

import java.sql.Date;
import java.util.List;

import com.diagnomind.web_server.domain.user.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table
public class Hospital {
    enum SubscriptionPlan {
        None,
        Standard,
        Deluxe,
    }

    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gid;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "uid")
    private List<User> user;

    @Column(nullable = false)
    private SubscriptionPlan subscriptionPlan;

    @Column(nullable = false)
    private Date subscriptionStart;

    @Column(nullable = false)
    private Date subsriptionEnd;
}
