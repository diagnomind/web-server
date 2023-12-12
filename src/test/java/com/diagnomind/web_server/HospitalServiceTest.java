package com.diagnomind.web_server;

import org.easymock.EasyMockSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.diagnomind.web_server.domain.hospital.repository.HospitalRepository;
import com.diagnomind.web_server.domain.hospital.service.HospitalService;
import com.diagnomind.web_server.domain.user.repository.UserRepository;

class HospitalServiceTest extends EasyMockSupport {
    private HospitalService hospitalService;
    private HospitalRepository mockHospitalRepository;
    private UserRepository mockUserRepository;

    @BeforeEach
    public void setUp() {
        mockHospitalRepository = createStrictMock(HospitalRepository.class);
        mockUserRepository = createStrictMock(UserRepository.class);
        hospitalService = new HospitalService(mockHospitalRepository, mockUserRepository);
    }

    @Test
    void addUserTest() {
        
    }

    @Test
    void getUserTest() {
        
    }

    @Test
    void getAllUsersTest() {
        
    }

    @Test
    void modifyUserTest() {
        
    }

    @Test
    void addHospitalTest() {

    }

    @Test
    void getHospitalTest() {

    }

    @Test
    void getAllHospitalsTest() {

    }

    @Test
    void modifyHospitalTest() {

    }

    @Test
    void deleteHospitalTest() {

    }
}
