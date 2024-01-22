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
 * 
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/diagnomind")
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
