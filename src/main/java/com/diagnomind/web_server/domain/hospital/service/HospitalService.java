package com.diagnomind.web_server.domain.hospital.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.diagnomind.web_server.domain.hospital.model.Hospital;
import com.diagnomind.web_server.domain.hospital.repository.HospitalRepository;
import com.diagnomind.web_server.domain.user.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;

    public Optional<User> addUser(Long gid, User user) {
        return hospitalRepository
                .findById(gid)
                .map(hospital -> {
                    hospital.getUsers().add(user);
                    return user;
                });
    }

    public Optional<User> getUser(Long gid, Long uid) {
        return hospitalRepository
                .findById(gid)
                .flatMap(hospital -> hospital
                        .getUsers()
                        .stream()
                        .filter(user -> user
                                .getId()
                                .equals(uid))
                        .findFirst());
    }

    public List<User> getAllUsers(Long gid) {
        return hospitalRepository
                .findById(gid)
                .map(Hospital::getUsers)
                .orElse(List.of());
    }

    public Optional<User> modifyUser(Long gid, User modifiedUser) {
        return hospitalRepository
                .findById(gid)
                .flatMap(hospital -> hospital
                        .getUsers()
                        .stream()
                        .filter(user -> user
                                .getId()
                                .equals(modifiedUser.getId()))
                        .findFirst()
                        .map(foundUser -> foundUser.update(modifiedUser)));
    }

    public boolean deleteUser(Long gid, Long uid) {
        return hospitalRepository
                .findById(gid)
                .map(hospital -> hospital
                        .getUsers()
                        .removeIf(user -> user
                                .getId()
                                .equals(uid)))
                .orElse(false);
    }

    public Hospital addHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public Optional<Hospital> getHospital(Long gid) {
        return hospitalRepository.findById(gid);
    }

    public List<Hospital> getAllHospitals() {
        return StreamSupport
                .stream(hospitalRepository.findAll().spliterator(), false)
                .toList();
    }

    public Optional<Hospital> modifyHospital(Hospital modifiedHospital) {
        return hospitalRepository
                .findById(modifiedHospital.getId())
                .map(hospital -> hospital.update(modifiedHospital));
    }

    public boolean deleteHospital(Long gid) {
        hospitalRepository.deleteById(gid);
        return !hospitalRepository.existsById(gid);
    }
}
