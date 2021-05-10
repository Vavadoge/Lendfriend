package com.ktu.vavadoge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {

    @Test
    void getName() {
        UserProfile user = new UserProfile();
        user.setName("TEST");
        assertEquals("TEST",user.getName());
    }

    @Test
    void setName() {
        UserProfile user = new UserProfile();
        user.setName("TEST");
        assertEquals("TEST",user.getName());
    }
}