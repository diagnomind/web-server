package com.diagnomind.web_server.controllers;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.diagnomind.web_server.controller.UserController;
import com.diagnomind.web_server.domain.hospital.model.Hospital;
import com.diagnomind.web_server.domain.hospital.service.HospitalService;
import com.diagnomind.web_server.domain.user.model.User;

class UserControllerTest extends EasyMockSupport {
    
    private static final Long GID = 1L;
    private static final Long UID = 1L;

    private Hospital hospital;
    private UserController usercontroller;
    private HospitalService mockHospitalService;

    @BeforeEach
    void setup() {
        mockHospitalService = createStrictMock(HospitalService.class);
        userController = new UserController(mockHospitalService);
        hospital = new Hospital();
        hospital.setId(GID);
    }

    @Test
    public void buscarUsuarioTest() {
        User user = new User();
        EasyMock.expect(mockHospitalService).getUser(GID, UID).andReturn(user);
        EasyMock.replay(mockHospitalService);
        ResponseEntity<User> response = userController.buscarUsuario(GID, UID)
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
        EasyMock.verify(mockHospitalService);
    }
    
}
