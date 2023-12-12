package com.diagnomind.web_server;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.easymock.EasyMock;
import org.easymock.EasyMockSupport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.diagnomind.web_server.domain.hospital.model.Hospital;
import com.diagnomind.web_server.domain.hospital.model.Hospital.SubscriptionPlan;
import com.diagnomind.web_server.domain.hospital.repository.HospitalRepository;
import com.diagnomind.web_server.domain.hospital.service.HospitalService;
import com.diagnomind.web_server.domain.user.model.User;

class HospitalServiceTest extends EasyMockSupport {

    private static final Integer GID = 1;
    private static final Integer UID = 1;

    private Hospital hospital;
    private HospitalService hospitalService;
    private HospitalRepository mockHospitalRepository;

    @BeforeEach
    public void setUp() {
        hospital = new Hospital();
        mockHospitalRepository = createStrictMock(HospitalRepository.class);
        hospitalService = new HospitalService(mockHospitalRepository);
    }

    @Test
    void addUserTest() {
        User user = new User();
        hospital.setUsers(new ArrayList<>());
        EasyMock.expect(mockHospitalRepository.findById(GID)).andReturn(Optional.of(hospital));
        EasyMock.replay(mockHospitalRepository);
        hospitalService.addUser(GID, user);
        assertTrue(hospital.getUsers().contains(user));
        EasyMock.verify(mockHospitalRepository);
    }

    @Test
    void getUserTest() {
        User user = new User();
        user.setUid(UID);
        hospital.setUsers(new ArrayList<>());
        hospital.getUsers().add(user);
        EasyMock.expect(mockHospitalRepository.findById(GID)).andReturn(Optional.of(hospital));
        EasyMock.replay(mockHospitalRepository);
        assertEquals(hospitalService.getUser(GID, UID).get(), user);
        EasyMock.verify(mockHospitalRepository);
    }

    @Test
    void getAllUsersTest() {
        List<User> allUsersList = new ArrayList<User>();
        hospital.setUsers(allUsersList);
        EasyMock.expect(mockHospitalRepository.findById(GID)).andReturn(Optional.of(hospital));
        EasyMock.replay(mockHospitalRepository);
        assertEquals(hospital.getUsers(), hospitalService.getAllUsers(GID));
        EasyMock.verify(mockHospitalRepository);
    }

    @Test
    void modifyUserTest() {
        hospital.setUsers(new ArrayList<>());
        User user = new User();
        user.setUid(UID);
        hospital.getUsers().add(user);
        EasyMock.expect(mockHospitalRepository.findById(GID)).andReturn(Optional.of(hospital));
        EasyMock.replay(mockHospitalRepository);
        User modifiedUser = new User();
        modifiedUser.setUid(UID);
        modifiedUser.setName("A");
        modifiedUser.setSurname("A");
        modifiedUser.setSsn("A");
        User returnedUser = hospitalService.modifyUser(GID, modifiedUser).get();
        assertEquals(modifiedUser.getName(), returnedUser.getName());
        assertEquals(modifiedUser.getSurname(), returnedUser.getSurname());
        assertEquals(modifiedUser.getSsn(), returnedUser.getSsn());
        EasyMock.verify(mockHospitalRepository);
    }

    @Test
    void deleteUserTest() {
        hospital.setUsers(new ArrayList<>());
        User user = new User();
        user.setUid(UID);
        hospital.getUsers().add(user);
        EasyMock.expect(mockHospitalRepository.findById(GID)).andReturn(Optional.of(hospital));
        EasyMock.replay(mockHospitalRepository);
        hospitalService.deleteUser(GID, UID);
        assertTrue(hospital.getUsers().isEmpty());
        EasyMock.verify(mockHospitalRepository);
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
        modifiedHospital.setGid(GID);
        modifiedHospital.setName("A");
        modifiedHospital.setSubscriptionPlan(SubscriptionPlan.NONE);
        modifiedHospital.setSubscriptionStart(new Date(1));
        modifiedHospital.setSubsriptionEnd(new Date(1));
        EasyMock.expect(mockHospitalRepository.findById(GID)).andReturn(Optional.of(hospital));
        EasyMock.replay(mockHospitalRepository);
        Hospital returnedHospital = hospitalService.modifyHospital(modifiedHospital).get();
        assertEquals(hospital, returnedHospital);
        assertEquals(modifiedHospital.getName(), returnedHospital.getName());
        assertEquals(modifiedHospital.getSubscriptionPlan(), returnedHospital.getSubscriptionPlan());
        assertEquals(modifiedHospital.getSubscriptionStart(), returnedHospital.getSubscriptionStart());
        assertEquals(modifiedHospital.getSubsriptionEnd(), returnedHospital.getSubsriptionEnd());
        EasyMock.verify(mockHospitalRepository);
    }

    @Test
    void deleteHospitalTest() {
        mockHospitalRepository.deleteById(GID);
        EasyMock.replay(mockHospitalRepository);
        hospitalService.deleteHospital(GID);
        EasyMock.verify(mockHospitalRepository);
    }
}
