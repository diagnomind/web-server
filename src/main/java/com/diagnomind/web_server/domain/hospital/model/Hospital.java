package com.diagnomind.web_server.domain.hospital.model;

import java.sql.Date;
import java.util.List;

import com.diagnomind.web_server.domain.user.model.User;

import jakarta.persistence.CascadeType;
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
/**
 * Represents a hospital entity with details such as name, subscription plan, and subscription period.
 * The class includes methods for updating hospital information.
 */
public class Hospital {
    
    /**
     * Enumeration representing possible subscription plans for the hospital.
     */
    public enum SubscriptionPlan {
        /*
         *no subcription plan 
         */
        NONE,
        /*
         *no subcription plan 
         */
        STANDARD,
        /*
         *Deluxe subcription plan 
         */
        DELUXE,
    }

    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;

    /**
     * The unique identifier for the hospital.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The name of the hospital. Cannot be null.
     */
    @Column(nullable = false, name = "name")
    private String name;

    /**
     * The subscription plan of the hospital. Cannot be null.
     */
    @Column(nullable = false, name = "subscription_plan")
    private SubscriptionPlan subscriptionPlan;

    /**
     * The start date of the hospital's subscription. Cannot be null.
     */
    @Column(nullable = false, name = "subscription_start")
    private Date subscriptionStart;

    /**
     * The end date of the hospital's subscription. Cannot be null.
     */
    @Column(nullable = false, name = "subscription_end")
    private Date subscriptionEnd;

    /**
     * The list of users associated with the hospital.
     */
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;

    /**
     * Updates the hospital information with the provided hospital's details.
     *
     * @param hospital The Hospital object containing the modified details to be applied.
     * @return The updated Hospital object.
    **/


    public Hospital update(Hospital hospital) {
        this.name = hospital.getName();
        this.subscriptionPlan = hospital.getSubscriptionPlan();
        this.subscriptionStart = hospital.getSubscriptionStart();
        this.subscriptionEnd = hospital.getSubscriptionEnd();
        return this;
    }
}
