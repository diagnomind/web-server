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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hospital")
public class Hospital {
    
    public enum SubscriptionPlan {
        NONE,
        STANDARD,
        DELUXE,
    }

    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gid")
    private Long gid;

    @Column(nullable = false, name = "name")
    private String name;

    @OneToMany(mappedBy = "uid")
    private List<User> users;

    @Column(nullable = false, name = "subscription_plan")
    private SubscriptionPlan subscriptionPlan;

    @Column(nullable = false, name = "subscription_start")
    private Date subscriptionStart;

    @Column(nullable = false, name = "subscription_end")
    private Date subsriptionEnd;

    public Hospital update(Hospital hospital) {
        this.name = hospital.getName();
        this.subscriptionPlan = hospital.getSubscriptionPlan();
        this.subscriptionStart = hospital.getSubscriptionStart();
        this.subsriptionEnd = hospital.getSubsriptionEnd();
        return this;
    }
}
