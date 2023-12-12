package com.diagnomind.web_server.domain.hospital.service;

import java.util.List;
import java.util.Optional;

import com.diagnomind.web_server.domain.hospital.model.Hospital;
import com.diagnomind.web_server.domain.hospital.repository.HospitalRepository;
import com.diagnomind.web_server.domain.user.model.User;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public Optional<User> addUser(Integer gid, User user) {
        
    }

    public Optional<User> getUser(Integer gid, Integer uid) {

    }

    public List<User> getAllUsers(Integer gid) {

    }

    public Optional<User> modifyUser(User user) {

    }

    public void addHospital(Hospital hospital) {

    }

    public void getHospital(Integer gid) {

    }

    public List<Hospital> getAllHospitals() {

    }

    public Optional<Hospital> modifyHospital(Hospital hospital) {

    }

    public void deleteHospital(Integer gid) {

    }
}
