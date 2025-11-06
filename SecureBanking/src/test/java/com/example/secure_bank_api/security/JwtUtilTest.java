package com.example.secure_bank_api.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class JwtUtilTest {
    @Test
    void tokenRoundtrip() {
        JwtUtil util = new JwtUtil();
        ReflectionTestUtils.setField(util, "secret", "abcdefghijklmnopqrstuvwxyz012345abcdefghijklmnopqrstuvwxyz01");
        ReflectionTestUtils.setField(util, "expiration", 3600000L);
        String t = util.generateToken("alice");
        String u = util.extractUsername(t);
        Assertions.assertEquals("alice", u);
        Assertions.assertTrue(util.validateToken(t, "alice"));
    }
}
