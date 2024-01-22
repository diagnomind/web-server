package com.diagnomind.web_server.domain.hospital.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.diagnomind.web_server.domain.hospital.model.Hospital;
import com.diagnomind.web_server.domain.hospital.repository.HospitalRepository;
import com.diagnomind.web_server.domain.user.model.User;
import com.diagnomind.web_server.domain.user.repository.UserRepository;

class HospitalServiceTest extends EasyMockSupport {
    private static final Long GID = 1L;
    private static final Long UID = 1L;

    private Hospital hospital;
    private HospitalService hospitalService;
    private HospitalRepository mockHospitalRepository;
    private UserRepository mockUserRepository;

    @BeforeEach
    public void setUp() {
        hospital = new Hospital();
        mockHospitalRepository = createStrictMock(HospitalRepository.class);
        mockUserRepository = createStrictMock(UserRepository.class);
        hospitalService = new HospitalService(mockHospitalRepository, mockUserRepository);
    }

    @Test
    void addUserTest() {
        User user = new User();
        hospital.setUsers(new ArrayList<>());
        EasyMock.expect(mockHospitalRepository.findById(GID)).andReturn(Optional.of(hospital));
        EasyMock.expect(mockUserRepository.save(user)).andReturn(user);
        EasyMock.replay(mockHospitalRepository, mockUserRepository);
        assertEquals(hospitalService.addUser(GID, user).get(), user);
        EasyMock.verify(mockHospitalRepository, mockUserRepository);
    }

    @Test
    void getUserTest() {
        User user = new User();
        user.setId(UID);
        hospital.setUsers(new ArrayList<>());
        hospital.getUsers().add(user);
        EasyMock.expect(mockHospitalRepository.findById(GID)).andReturn(Optional.of(hospital));
        EasyMock.replay(mockHospitalRepository);
        assertEquals(hospitalService.getUser(GID, UID).get(), user);
        EasyMock.verify(mockHospitalRepository);
    }

    @Test
    void getAllUsersTest() {
        List<User> allUsersList = new ArrayList<>();
        hospital.setUsers(allUsersList);
        EasyMock.expect(mockHospitalRepository.findById(GID)).andReturn(Optional.of(hospital));
        EasyMock.replay(mockHospitalRepository);
        assertEquals(hospital.getUsers(), hospitalService.getAllUsers(GID));
        EasyMock.verify(mockHospitalRepository);
    }

    @Test
    void modifyUserTest() {
        User user = new User();
        EasyMock.expect(mockHospitalRepository.findById(GID)).andReturn(Optional.of(hospital));
        EasyMock.expect(mockUserRepository.save(user)).andReturn(user);
        EasyMock.replay(mockHospitalRepository, mockUserRepository);
        assertEquals(hospitalService.modifyUser(GID, user).get(), user);
        EasyMock.verify(mockHospitalRepository);
    }

    @Test
    void deleteUserCorrectTest() {
        mockUserRepository.deleteById(UID);
        EasyMock.expect(mockUserRepository.existsById(UID)).andReturn(false);
        EasyMock.replay(mockUserRepository);
        assertTrue(hospitalService.deleteUser(UID));
        EasyMock.verify(mockUserRepository);
    }

    @Test
    void deleteUserFailTest() {
        mockUserRepository.deleteById(UID);
        EasyMock.expect(mockUserRepository.existsById(UID)).andReturn(true);
        EasyMock.replay(mockUserRepository);
        assertFalse(hospitalService.deleteUser(UID));
        EasyMock.verify(mockUserRepository);
    }

    @Test
    void addHospitalTest() {
        EasyMock.expect(mockHospitalRepository.save(hospital)).andReturn(hospital);
        EasyMock.replay(mockHospitalRepository);
        hospitalService.addHospital(hospital);
        EasyMock.verify(mockHospitalRepository);
    }

    @Test
    void getHospitalTest() {
        EasyMock.expect(mockHospitalRepository.findById(GID)).andReturn(Optional.of(hospital));
        EasyMock.replay(mockHospitalRepository);
        assertEquals(hospital, hospitalService.getHospital(GID).get());
        EasyMock.verify(mockHospitalRepository);
    }

    @Test
    void getAllHospitalsTest() {
        List<Hospital> hospitals = new ArrayList<>();
        EasyMock.expect(mockHospitalRepository.findAll()).andReturn(hospitals);
        EasyMock.replay(mockHospitalRepository);
        assertEquals(hospitals, hospitalService.getAllHospitals());
        EasyMock.verify(mockHospitalRepository);
    }

    @Test
    void modifyHospitalTest() {
        Hospital modifiedHospital = new Hospital();
        EasyMock.expect(mockHospitalRepository.findById(GID)).andReturn(Optional.of(hospital));
        EasyMock.expect(mockHospitalRepository.save(hospital)).andReturn(modifiedHospital);
        EasyMock.replay(mockHospitalRepository);
        assertEquals(hospitalService.modifyHospital(GID, modifiedHospital).get(), modifiedHospital);
        EasyMock.verify(mockHospitalRepository);
    }

    @Test
    void deleteHospitalCorrectTest() {
        mockHospitalRepository.deleteById(GID);
        EasyMock.expect(mockHospitalRepository.existsById(GID)).andReturn(false);
        EasyMock.replay(mockHospitalRepository);
        assertTrue(hospitalService.deleteHospital(GID));
        EasyMock.verify(mockHospitalRepository);
    }

    @Test
    void deleteHospitalFailTest() {
        mockHospitalRepository.deleteById(GID);
        EasyMock.expect(mockHospitalRepository.existsById(GID)).andReturn(true);
        EasyMock.replay(mockHospitalRepository);
        assertFalse(hospitalService.deleteHospital(GID));
        EasyMock.verify(mockHospitalRepository);
    }
}
