package com.diagnomind.web_server.domain.hospital.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.diagnomind.web_server.domain.hospital.model.Hospital.SubscriptionPlan;

class HospitalTest {
    
    private Hospital hospital;

    @BeforeEach
    void setUp() {
        hospital = new Hospital();
    }

    @Test
    void updateTest() {
        Hospital newHospital = new Hospital();
        newHospital.setName("A");
        newHospital.setSubscriptionPlan(SubscriptionPlan.DELUXE);
        newHospital.setSubscriptionStart(new Date(1));
        newHospital.setSubscriptionEnd(new Date(1));
        hospital.update(newHospital);
        assertEquals(hospital.getName(), newHospital.getName());
        assertEquals(hospital.getSubscriptionPlan(), hospital.getSubscriptionPlan());
        assertEquals(hospital.getSubscriptionStart(), newHospital.getSubscriptionStart());
        assertEquals(hospital.getSubscriptionEnd(), newHospital.getSubscriptionEnd());
    }
}
