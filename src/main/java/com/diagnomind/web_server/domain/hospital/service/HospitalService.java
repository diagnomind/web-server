package com.diagnomind.web_server.domain.hospital.service;

import java.util.List;
import java.util.Optional;

import com.diagnomind.web_server.domain.hospital.model.Hospital;
import com.diagnomind.web_server.domain.hospital.repository.HospitalRepository;
import com.diagnomind.web_server.domain.user.model.User;
import com.diagnomind.web_server.domain.user.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    Optional<User> addUser(Integer gid, User user) {
        
    }

    Optional<User> getUser(Integer gid, Integer uid) {

    }

    List<User> getAllUsers(Integer gid) {

    }

    Optional<User> modifyUser(User user) {

    }

    void addHospital(Hospital hospital) {

    }

    void getHospital(Integer gid) {

    }

    List<Hospital> getAllHospitals() {

    }

    Optional<Hospital> modifyHospital(Hospital hospital) {

    }

    void deleteHospital(Integer gid) {

    }
}
