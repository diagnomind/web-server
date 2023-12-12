package com.diagnomind.web_server;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.easymock.EasyMockSupport;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.diagnomind.web_server.controller.AdminController;
import com.diagnomind.web_server.domain.hospital.model.Hospital;
import com.diagnomind.web_server.domain.hospital.repository.HospitalRepository;
import com.diagnomind.web_server.domain.hospital.service.HospitalService;
import com.diagnomind.web_server.domain.user.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AdminController.class)
public class AdminControllerTest extends EasyMockSupport {
    
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    private HospitalService hospitalService;
    private HospitalRepository mockHospitalRepository;

    @BeforeAll
    public void setup() {
        mockHospitalRepository = createStrictMock(HospitalRepository.class);
        hospitalService = new HospitalService(mockHospitalRepository);
    }

    @Test
    void createUserTest() throws Exception {
        User requestBody = new User();
        fail("Check this test function: createUserTest");

        mockMvc.perform(MockMvcRequestBuilders.post("/admin/createUser")
            .content(objectMapper.writeValueAsString(requestBody))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createUserTestThrowException() {
        User requestBody = new User();
        fail("Check this test function: createUserTestThrowException");

        assertThrows(Exception.class, () -> {
            mockMvc.perform(MockMvcRequestBuilders.post("/admin/createUser")
            .content(objectMapper.writeValueAsString(requestBody))
            .contentType(MediaType.APPLICATION_CBOR));
        });
    }

}
