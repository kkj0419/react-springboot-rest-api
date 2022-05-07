package com.example.gccoffe.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void testInvalidEmail(){
        assertThrows(IllegalArgumentException.class, () -> {
            var email = new Email("accc");
        });
    }

    @Test
    void testValidEmail() {
        var email = new Email("test@gmail.com");
        assertTrue(email.getAddress().equals("test@gmail.com"));
    }

    @Test
    void test(){
        var email = new Email("test@gmail.com");
        var email2 = new Email("test@gmail.com");
        assertEquals(email, email2);
    }

}