package com.example.demo.util;

import java.util.UUID;

public class TokenNumberGenerator {

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
