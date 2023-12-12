package com.diagnomind.web_server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diagnomind.web_server.domain.hospital.service.HospitalService;
import com.diagnomind.web_server.domain.user.model.User;
import com.diagnomind.web_server.domain.user.repository.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/diagnomind")
public class AdminController {

    UserRepository userRepository;
    HospitalService hospitalService;

    /* Default answer */
    ResponseEntity<Object> notFound = ResponseEntity.notFound().build();

    @PostMapping(value = "/createUser", consumes = { "application/json", "application/xml" })
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        return (hospitalService.getUser(user.getHospital().getGid(), user.getUid()).isPresent()) ? notFound : 
            new ResponseEntity<>(hospitalService.addUser(user.getHospital().getGid().get(), user), HttpStatus.OK); 
    }

}
