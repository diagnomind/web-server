package com.diagnomind.web_server.domain.hospital.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.diagnomind.web_server.domain.hospital.model.Hospital;
import com.diagnomind.web_server.domain.hospital.repository.HospitalRepository;
import com.diagnomind.web_server.domain.user.model.User;
import com.diagnomind.web_server.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * The {@code HospitalService} class provides services related to hospital
 * operations.
 *
 * <p>
 * This class is annotated with {@link org.springframework.stereotype.Service},
 * indicating
 * that it serves as a service component in a Spring application. It is also
 * annotated with
 * {@link lombok.RequiredArgsConstructor}, which automatically generates a
 * constructor with
 * required arguments.
 *
 * <p>
 * The services provided by this class include operations for managing users
 * associated with hospitals.
 *
 * @author Diagnomind
 * @version 1.0
 * @since 2023-12-15
 */
@Service
@RequiredArgsConstructor
public class HospitalService {

    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;

    /**
     * Adds a user to a hospital based on the hospital's ID.
     *
     * @param gid  The ID of the hospital to which the user will be added.
     * @param user The User object to be added to the hospital.
     * @return An Optional containing the User object if the hospital with the given
     *         ID is found,
     *         or an empty Optional if the hospital is not found.
     */
    public Optional<User> addUser(Long gid, User user) {
        return hospitalRepository.findById(gid).map(hospital -> {
            user.setHospital(hospital);
            return userRepository.save(user);
        });
    }

    /**
     * Retrieves a user from a specific hospital based on hospital and user IDs.
     *
     * @param gid The ID of the hospital to search for.
     * @param uid The ID of the user to retrieve.
     * @return An Optional containing the User object if both the hospital and user
     *         are found,
     *         or an empty Optional if either the hospital or user is not found.
     */
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

    /**
     * Retrieves a list of all users associated with a specific hospital based on
     * the hospital ID.
     *
     * @param gid The ID of the hospital to retrieve users from.
     * @return A List containing all User objects associated with the specified
     *         hospital,
     *         or an empty List if the hospital is not found.
     */
    public List<User> getAllUsers(Long gid) {
        return hospitalRepository
                .findById(gid)
                .map(Hospital::getUsers)
                .orElse(List.of());
    }

    /**
     * Modifies a user in a specific hospital based on hospital ID and the modified
     * user details.
     *
     * @param gid          The ID of the hospital where the user is to be modified.
     * @param modifiedUser The User object containing the modified details to be
     *                     applied.
     * @return An Optional containing the modified User object if both the hospital
     *         and user are found,
     *         or an empty Optional if either the hospital or user is not found.
     */
    public Optional<User> modifyUser(Long gid, User modifiedUser) {
        return hospitalRepository.findById(gid).map(hospital -> {
            modifiedUser.setHospital(hospital);
            return userRepository.save(modifiedUser);
        });
    }

    /**
     * Deletes a user from a specific hospital based on hospital and user IDs.
     *
     * @param gid The ID of the hospital where the user is to be deleted.
     * @param uid The ID of the user to be deleted.
     * @return {@code true} if the user is successfully deleted, {@code false} if
     *         either the hospital
     *         or user is not found, or if the user deletion operation fails.
     */
    public boolean deleteUser(Long uid) {
        userRepository.deleteById(uid);
        return !userRepository.existsById(uid);
    }

    /**
     * Adds a new hospital to the repository.
     *
     * @param hospital The Hospital object to be added.
     * @return The Hospital object that has been saved in the repository.
     */
    public Hospital addHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    /**
     * Retrieves a hospital from the repository based on its ID.
     *
     * @param gid The ID of the hospital to retrieve.
     * @return An Optional containing the Hospital object if found,
     *         or an empty Optional if the hospital with the given ID is not present
     *         in the repository.
     */
    public Optional<Hospital> getHospital(Long gid) {
        return hospitalRepository.findById(gid);
    }

    /**
     * Retrieves a list of all hospitals from the repository.
     *
     * @return A List containing all Hospital objects stored in the repository.
     */
    public List<Hospital> getAllHospitals() {
        return StreamSupport
                .stream(hospitalRepository.findAll().spliterator(), false)
                .toList();
    }

    /**
     * Modifies a hospital based on the provided modified hospital details.
     *
     * @param modifiedHospital The Hospital object containing the modified details
     *                         to be applied.
     * @return An Optional containing the modified Hospital object if found,
     *         or an empty Optional if the hospital with the given ID is not present
     *         in the repository.
     */
    public Optional<Hospital> modifyHospital(Long gid, Hospital modifiedHospital) {
        return hospitalRepository.findById(gid).map(hospital -> {
            modifiedHospital.setId(gid);
            modifiedHospital.setUsers(hospital.getUsers());
            return hospitalRepository.save(hospital);
        });
    }

    /**
     * Deletes a hospital from the repository based on its ID.
     *
     * @param gid The ID of the hospital to be deleted.
     * @return {@code true} if the hospital is successfully deleted, {@code false}
     *         otherwise.
     */
    public boolean deleteHospital(Long gid) {
        hospitalRepository.deleteById(gid);
        return !hospitalRepository.existsById(gid);
    }
}
