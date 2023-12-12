package com.diagnomind.web_server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diagnomind.web_server.domain.hospital.service.HospitalService;
import com.diagnomind.web_server.domain.user.model.User;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final HospitalService hospitalService;

    @PostMapping(value = "/createUser", consumes = { "application/json", "application/xml" })
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return hospitalService
                .addUser(user.getHospital().getGid(), user)
                .map(newUser -> new ResponseEntity<>(newUser, HttpStatus.CREATED))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
    }

    @DeleteMapping(value = "/deleteUser")
    public ResponseEntity deleteUser() {
        return hospitalService;
    }

}
