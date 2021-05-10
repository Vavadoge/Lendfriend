package com.ktu.vavadoge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserFriendTest {

    @Test
    void getUsername() {
        UserFriend friend = new UserFriend("Dog", "A", "B" );
        friend.setUsername("TEST");
        assertEquals("TEST", friend.getUsername());
    }

    @Test
    void setUsername() {
        UserFriend friend = new UserFriend("Dog", "A", "B" );
        friend.setUsername("TEST");
        assertEquals("TEST", friend.getUsername());
    }

    @Test
    void getType() {
        UserFriend friend = new UserFriend("Dog", "A", "B" );
        friend.setType("TEST");
        assertEquals("TEST", friend.getType());
    }

    @Test
    void setType() {
        UserFriend friend = new UserFriend("Dog", "A", "B" );
        friend.setType("TEST");
        assertEquals("TEST", friend.getType());
    }

    @Test
    void getStatus() {
        UserFriend friend = new UserFriend("Dog", "A", "B" );
        friend.setStatus("TEST");
        assertEquals("TEST", friend.getStatus());
    }

    @Test
    void setStatus() {
        UserFriend friend = new UserFriend("Dog", "A", "B" );
        friend.setStatus("TEST");
        assertEquals("TEST", friend.getStatus());
    }
}