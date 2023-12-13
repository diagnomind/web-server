package com.diagnomind.web_server;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    private static final Integer GID = 1;
    private static final Integer UID = 1;

    private HospitalService mockHospitalService;
    private AdminController adminController;
    private Hospital hospital;

    @BeforeEach
    void setUp() {
        mockHospitalService = createStrictMock(HospitalService.class);
        adminController = new AdminController(mockHospitalService);
        hospital = new Hospital();
        hospital.setGid(GID);
    }

    @Test
    void createUserTest() {
        User user = new User();
        user.setHospital(hospital);
        EasyMock.expect(mockHospitalService.addUser(GID, user)).andReturn(Optional.of(user));
        EasyMock.replay(mockHospitalService);
        ResponseEntity<User> response = adminController.createUser(user);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(user, response.getBody());
        EasyMock.verify(mockHospitalService);
    }

    @Test
    void deleteUserCorrectTest() {
        EasyMock.expect(mockHospitalService.deleteUser(GID, UID)).andReturn(true);
        EasyMock.replay(mockHospitalService);
        assertEquals(HttpStatus.OK, adminController.deleteUser(GID, UID).getStatusCode());
        EasyMock.verify(mockHospitalService);
    }

    @Test
    void deleteUserFailTest() {
        EasyMock.expect(mockHospitalService.deleteUser(GID, UID)).andReturn(false);
        EasyMock.replay(mockHospitalService);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, adminController.deleteUser(GID, UID).getStatusCode());
        EasyMock.verify(mockHospitalService);
    }

    @Test
    void modifyUserTest() {
        User user = new User();
        user.setHospital(hospital);
        EasyMock.expect(mockHospitalService.modifyUser(GID, user)).andReturn(Optional.of(user));
        EasyMock.replay(mockHospitalService);
        ResponseEntity<User> response = adminController.modifyUser(user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        EasyMock.verify(mockHospitalService);
    }

    @Test
    void createHospitalTest() {
        EasyMock.expect(mockHospitalService.addHospital(hospital)).andReturn(hospital);
        EasyMock.replay(mockHospitalService);
        ResponseEntity<Hospital> response = adminController.createHospital(hospital);
        assertEquals(HttpStatus.OK, response.getStatusCode());
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
        EasyMock.expect(mockHospitalService.modifyHospital(hospital)).andReturn(Optional.of(hospital));
        EasyMock.replay(mockHospitalService);
        ResponseEntity<Hospital> response = adminController.modifyHospital(hospital);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hospital, response.getBody());
        EasyMock.verify(mockHospitalService);
    }
}