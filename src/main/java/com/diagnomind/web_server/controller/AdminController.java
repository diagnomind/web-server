package com.diagnomind.web_server.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diagnomind.web_server.domain.hospital.model.Hospital;
import com.diagnomind.web_server.domain.hospital.service.HospitalService;
import com.diagnomind.web_server.domain.user.model.User;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

/**
 * The {@code AdminController} class handles administrative operations
 * and exposes endpoints related to administration tasks.
 *
 * <p>
 * This class is annotated with
 * {@link org.springframework.web.bind.annotation.RestController},
 * indicating that it serves as a controller for handling RESTful web service
 * requests.
 * It is also annotated with {@link lombok.RequiredArgsConstructor}, which
 * automatically generates
 * a constructor with required arguments based on the class fields marked with
 * {@code final}.
 *
 * <p>
 * All endpoints in this controller are mapped under the base path
 * {@code "/admin"}.
 *
 * @author Diagnomind
 * @version 1.0
 * @since 2023-12-15
 */
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class AdminController {

    private final HospitalService hospitalService;

    /**
     * Creates a new user associated with a hospital and returns the corresponding
     * response.
     *
     * <p>
     * This method is mapped to the HTTP POST request for the path
     * {@code "/admin/createUser"}.
     * It consumes JSON or XML data in the request body. The method delegates the
     * user creation
     * operation to the {@link HospitalService} and returns an appropriate response.
     *
     * <p>
     * If the user creation is successful, it responds with a status of
     * {@link HttpStatus#CREATED}
     * and includes the newly created user in the response body. If the user
     * creation fails or the
     * specified hospital does not exist, it responds with a status of
     * {@link HttpStatus#NOT_ACCEPTABLE}.
     *
     * @param user The user information provided in the request body.
     * @return A {@link ResponseEntity} containing the created user or an error
     *         status.
     * 
     * @see HospitalService#addUser(Long, User)
     *      {@link HospitalService#addUser(Long, User)}, method
     */
    @PostMapping(value = "/createUser/{gid}", consumes = { "application/json", "application/xml" })
    public ResponseEntity<User> createUser(@PathVariable Long gid, @RequestBody User user) {
        return hospitalService
                .addUser(gid, user)
                .map(newUser -> new ResponseEntity<>(newUser, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
    }

    /**
     * Deletes a user associated with a hospital based on the specified group ID and
     * user ID.
     *
     * <p>
     * This method is mapped to the HTTP DELETE request for the path
     * {@code "/admin/deleteUser/{gid}/{uid}"}. It consumes JSON or XML data in the
     * request body.
     * The method delegates the user deletion operation to the
     * {@link HospitalService} and returns
     * an appropriate response.
     *
     * <p>
     * If the user deletion is successful, it responds with a status of
     * {@link HttpStatus#OK}.
     * If the user deletion fails or the specified hospital or user does not exist,
     * it responds
     * with a status of {@link HttpStatus#NOT_ACCEPTABLE}.
     *
     * @param gid The group ID associated with the hospital.
     * @param uid The user ID to be deleted.
     * @return A {@link ResponseEntity} indicating the success or failure of the
     *         user deletion operation.
     *
     * @see HospitalService#deleteUser(Long, Long)
     *      {@link HospitalService#deleteUser(Long, Long)}, method
     */
    @DeleteMapping(value = "/deleteUser/{uid}")
    public ResponseEntity<User> deleteUser(@PathVariable Long uid) {
        return hospitalService.deleteUser(uid) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Modifies the information of an existing user associated with a hospital and
     * returns the corresponding response.
     *
     * <p>
     * This method is mapped to the HTTP PUT request for the path
     * {@code "/admin/modifyUser"}.
     * It consumes JSON or XML data in the request body. The method delegates the
     * user modification
     * operation to the {@link HospitalService} and returns an appropriate response.
     *
     * <p>
     * If the user modification is successful, it responds with a status of
     * {@link HttpStatus#OK}
     * and includes the modified user in the response body. If the user modification
     * fails or the
     * specified hospital or user does not exist, it responds with a status of
     * {@link HttpStatus#NOT_MODIFIED}.
     *
     * @param user The modified user information provided in the request body.
     * @return A {@link ResponseEntity} containing the modified user or an error
     *         status.
     *
     * @see HospitalService#modifyUser(Long, User)
     *      {@link HospitalService#modifyUser(Long, User)}, method
     */
    @PutMapping(value = "/updateUser/{gid}", produces = { "application/json", "application/xml" })
    public ResponseEntity<User> updateUser(@PathVariable Long gid, @RequestBody User user) {
        return hospitalService
                .modifyUser(gid, user)
                .map(modifiedUser -> new ResponseEntity<>(modifiedUser, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
    }

    /**
     * Creates a new hospital by adding it to the repository.
     *
     * @param hospital The Hospital object representing the hospital to be created.
     * @return A ResponseEntity containing the created Hospital object and HTTP
     *         status OK if successful,
     *         or an appropriate HTTP status if an error occurs.
     * 
     * @see HospitalService#addHospital(Hospital)
     *      {@link HospitalService#addHospital(Hospital)}, method
     */
    @PostMapping(value = "/createHospital", consumes = { "application/json", "application/xml" })
    public ResponseEntity<Hospital> createHospital(@RequestBody Hospital hospital) {
        return new ResponseEntity<>(hospitalService.addHospital(hospital), HttpStatus.CREATED);
    }

    /**
     * Deletes a hospital from the repository based on its ID.
     *
     * @param gid The ID of the hospital to be deleted.
     * @return A ResponseEntity with HTTP status OK if the hospital is successfully
     *         deleted,
     *         or HTTP status NOT_ACCEPTABLE if the hospital deletion operation
     *         fails.
     * 
     * @see HospitalService#deleteHospital(Long)
     *      {@link HospitalService#deleteHospital(Long)}, method
     */
    @DeleteMapping(value = "/deleteHospital/{gid}", produces = { "application/json", "application/xml" })
    public ResponseEntity<Object> deleteHospital(@PathVariable long gid) {
        return (hospitalService.deleteHospital(gid)) ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * Modifies a hospital based on the provided modified hospital details.
     *
     * @param hospital The Hospital object containing the modified details to be
     *                 applied.
     * @return A ResponseEntity containing the modified Hospital object and HTTP
     *         status OK if successful,
     *         or HTTP status NOT_MODIFIED if the hospital modification operation
     *         fails.
     * 
     * @see HospitalService#modifyHospital(Hospital)
     *      {@link HospitalService#modifyHospital(Hospital)}, method
     */
    @PutMapping(value = "/modifyHospital/{gid}", consumes = { "application/json", "application/xml" })
    public ResponseEntity<Hospital> modifyHospital(@PathVariable Long gid, @RequestBody Hospital hospital) {
        return hospitalService
                .modifyHospital(gid, hospital)
                .map(modifiedHospital -> new ResponseEntity<>(modifiedHospital, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
    }

    /**
     * Retrieves a list of users associated with a specific hospital ID and returns it as a ResponseEntity.
     *
     * @param gid The ID of the hospital for which users are to be retrieved.
     * @return A ResponseEntity containing a List of User objects and HTTP status OK if successful,
     *         or HTTP status NOT_FOUND if no users are found for the specified hospital.
     * 
     * @see HospitalService#getAllUsers(Long)
     *      {@link HospitalService#getAllUsers(Long)}, method
     */
    @GetMapping(value = "/showUsers/{gid}", produces = { "application/json", "application/xml" })
    public ResponseEntity<List<User>> getUsers(@PathVariable Long gid) {
        List<User> userList = hospitalService.getAllUsers(gid);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(userList, headers, HttpStatus.OK);
    }

    /**
     * Retrieves a list of all hospitals and returns it as a ResponseEntity.
     *
     * @return A ResponseEntity containing a List of Hospital objects and HTTP status OK if successful,
     *         or an appropriate HTTP status if an error occurs.
     */
    @GetMapping(value = "/showHospitals", produces = { "application/json", "application/xml" })
    public ResponseEntity<List<Hospital>> getHospitals() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(hospitalService.getAllHospitals(), headers, HttpStatus.OK);
    }

}
