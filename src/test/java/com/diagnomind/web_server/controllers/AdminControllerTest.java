package com.diagnomind.web_server.controllers;

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
import com.diagnomind.web_server.domain.training_image.model.TrainingImage;
import com.diagnomind.web_server.domain.training_image.service.TrainingImageService;
import com.diagnomind.web_server.domain.user.model.User;

class AdminControllerTest extends EasyMockSupport {

    private static final Long GID = 1L;
    private static final Long UID = 1L;

    private HospitalService mockHospitalService;
    private TrainingImageService mockTrainingImageService;
    private AdminController adminController;
    private Hospital hospital;

    @BeforeEach
    void setUp() {
        mockHospitalService = createStrictMock(HospitalService.class);
        mockTrainingImageService = createStrictMock(TrainingImageService.class);
        adminController = new AdminController(mockHospitalService, mockTrainingImageService);
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

    @Test
    void uploadImageTest() {
        TrainingImage trainingImage = new TrainingImage();
        EasyMock.expect(mockTrainingImageService.addTrainImage(trainingImage)).andReturn(trainingImage);
        EasyMock.replay(mockTrainingImageService);
        ResponseEntity<TrainingImage> response = adminController.uploadImage(trainingImage);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(trainingImage, response.getBody());
        EasyMock.verify(mockTrainingImageService);
    }
}