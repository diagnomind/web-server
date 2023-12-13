package com.diagnomind.web_server.controller;

import org.easymock.internal.matchers.Null;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diagnomind.web_server.domain.hospital.model.Hospital;
import com.diagnomind.web_server.domain.hospital.service.HospitalService;
import com.diagnomind.web_server.domain.user.model.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
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

    @DeleteMapping(value = "/deleteUser/{gid}/{uid}", consumes = { "application/json", "application/xml" })
    public ResponseEntity deleteUser(@PathVariable int gid, @PathVariable int uid) {
        return (hospitalService.deleteUser(gid, uid)) ? new ResponseEntity<>(HttpStatus.OK) : 
                new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping(value = "/modifyUser", consumes = { "application/json", "application/xml" })
    public ResponseEntity<User> modifyUser(@RequestBody User user) {
        return hospitalService
                .modifyUser(user.getHospital().getGid(), user)
                .map(modifiedUser -> new ResponseEntity<>(modifiedUser, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
    }

    @PostMapping(value = "/createHospital", consumes = { "application/json", "application/xml" })
    public ResponseEntity<Hospital> createHospital(@RequestBody Hospital hospital) {
        return (hospital.equals(null)) ? new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE) :
            new ResponseEntity<>(hospitalService.addHospital(hospital), HttpStatus.OK);
    }

   @DeleteMapping(value = "deleteHospital/{gid}", consumes = { "application/json", "application/xml" })
    public ResponseEntity deleteHospital(@PathVariable int gid) {
        return (hospitalService.deleteHospital(gid)) ? new ResponseEntity<>(HttpStatus.OK) : 
                new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping(value = "/modifyHospital", consumes = { "application/json", "application/xml" })
    public ResponseEntity<Hospital> modifyHospital(@RequestBody Hospital hospital) {
        return hospitalService
                .modifyHospital(hospital)
                .map(modifiedHospital -> new ResponseEntity<>(modifiedHospital, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
    }

}
