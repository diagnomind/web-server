package com.diagnomind.web_server.domain.user.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
    User user;

    @BeforeEach
    void setUp() {
        user = new User();
    }

    @Test
    void updateTest() {
        User newUser = new User();
        newUser.setName("A");
        newUser.setSurname("A");
        newUser.setSsn("A");
        user.update(newUser);
        assertEquals(user.getName(), newUser.getName());
        assertEquals(user.getSurname(), newUser.getSurname());
        assertEquals(user.getSsn(), newUser.getSsn());
    }
}
