package com.diagnomind.web_server.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.diagnomind.web_server.controller.AdminController;
import com.diagnomind.web_server.domain.hospital.model.Hospital;
import com.diagnomind.web_server.domain.hospital.service.HospitalService;
import com.diagnomind.web_server.domain.user.model.User;

class AdminControllerTest extends EasyMockSupport {

    private static final Long GID = 1L;
    private static final Long UID = 1L;

    private HospitalService mockHospitalService;
    private AdminController adminController;
    private Hospital hospital;

    @BeforeEach
    void setUp() {
        mockHospitalService = createStrictMock(HospitalService.class);
        adminController = new AdminController(mockHospitalService);
        hospital = new Hospital();
        hospital.setId(GID);
    }

    @Test
    void createUserTest() {
        User user = new User();
        user.setHospital(hospital);
        EasyMock.expect(mockHospitalService.addUser(GID, user)).andReturn(Optional.of(user));
        EasyMock.replay(mockHospitalService);
        ResponseEntity<User> response = adminController.createUser(GID, user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
        EasyMock.verify(mockHospitalService);
    }

    @Test
    void deleteUserCorrectTest() {
        EasyMock.expect(mockHospitalService.deleteUser(UID)).andReturn(true);
        EasyMock.replay(mockHospitalService);
        assertEquals(HttpStatus.OK, adminController.deleteUser(UID).getStatusCode());
        EasyMock.verify(mockHospitalService);
    }

    @Test
    void deleteUserFailTest() {
        EasyMock.expect(mockHospitalService.deleteUser(UID)).andReturn(false);
        EasyMock.replay(mockHospitalService);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, adminController.deleteUser(UID).getStatusCode());
        EasyMock.verify(mockHospitalService);
    }

    @Test
    void updateUserTest() {
        User user = new User();
        user.setHospital(hospital);
        EasyMock.expect(mockHospitalService.modifyUser(GID, user)).andReturn(Optional.of(user));
        EasyMock.replay(mockHospitalService);
        ResponseEntity<User> response = adminController.updateUser(GID, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        EasyMock.verify(mockHospitalService);
    }

    @Test
    void createHospitalTest() {
        EasyMock.expect(mockHospitalService.addHospital(hospital)).andReturn(hospital);
        EasyMock.replay(mockHospitalService);
        ResponseEntity<Hospital> response = adminController.createHospital(hospital);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(hospital, response.getBody());
        EasyMock.verify(mockHospitalService);
    }

    @Test
    void deleteHospitalCorrectTest() {
        EasyMock.expect(mockHospitalService.deleteHospital(GID)).andReturn(true);
        EasyMock.replay(mockHospitalService);
        ResponseEntity<Object> response = adminController.deleteHospital(GID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        EasyMock.verify(mockHospitalService);
    }

    @Test
    void deleteHospitalFailTest() {
        EasyMock.expect(mockHospitalService.deleteHospital(GID)).andReturn(false);
        EasyMock.replay(mockHospitalService);
        ResponseEntity<Object> response = adminController.deleteHospital(GID);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
        EasyMock.verify(mockHospitalService);
    }

    @Test
    void modifyHospitalTest() {
        EasyMock.expect(mockHospitalService.modifyHospital(GID, hospital)).andReturn(Optional.of(hospital));
        EasyMock.replay(mockHospitalService);
        ResponseEntity<Hospital> response = adminController.modifyHospital(GID, hospital);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hospital, response.getBody());
        EasyMock.verify(mockHospitalService);
    }

    @Test
    void getUsersTest() {
        User user = new User();
        List<User> list = new ArrayList<>();
        list.add(user);
        EasyMock.expect(mockHospitalService.getAllUsers(GID)).andReturn(list);
        EasyMock.replay(mockHospitalService);
        ResponseEntity<List<User>> response = adminController.getUsers(GID);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        EasyMock.verify(mockHospitalService);
    }

    @Test
    void getHospitalsTest() {
        List<Hospital> list = new ArrayList<>();
        list.add(hospital);
        EasyMock.expect(mockHospitalService.getAllHospitals()).andReturn(list);
        EasyMock.replay(mockHospitalService);
        ResponseEntity<List<Hospital>> response = adminController.getHospitals();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        EasyMock.verify(mockHospitalService);
    }

}