package com.diagnomind.web_server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diagnomind.web_server.domain.hospital.service.HospitalService;
import com.diagnomind.web_server.domain.user.model.User;
import lombok.RequiredArgsConstructor;

/**
 * The {@code UserController} class handles administrative operations
 * and exposes endpoints related to user tasks.
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
 *
 * @author Diagnomind
 * @version 1.0
 * @since 2023-12-15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/diagnomind")

/**
 * Search for a User inside of and Hospital and send it back
 *
 * <p>
 * This method is mapped to the HTTP Get request for the path
 * {@code ""/login/{gid}/{uid}"}.
 * It consumes JSON or XML data in the request body. The method delegates the
 * user creation
 * operation to the {@link HospitalService} and returns an appropriate response.
 *
 * <p>
 * If the user exist, it responds with a status of
 * {@link HttpStatus#CREATED}
 * and includes the user in the response body. If the 
 * specified user does not exist, it responds with a status of
 * {@link HttpStatus#NOT_ACCEPTABLE}.
 *
 * @param gid The id of the Hospital to search in.
 * @param uid The id of the User to search.
 * @return A {@link ResponseEntity} containing the created user or an error
 *         status.
 * 
 * @see HospitalService#addUser(Long, User)
 *      {@link HospitalService#addUser(Long, User)}, method
 */
public class UserController {

    private final HospitalService hospitalService;

    @GetMapping(value = "/login/{gid}/{uid}", produces = { "application/json", "application/json" })
    public ResponseEntity<User> buscarUsuario(@PathVariable Long gid, @PathVariable Long uid) {
        return hospitalService
                .getUser(gid, uid)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
    }
}
