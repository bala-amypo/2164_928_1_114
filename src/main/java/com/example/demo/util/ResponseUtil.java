package com.example.demo.util;

import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    private ResponseUtil() {}

    public static <T> ResponseEntity<T> ok(T body) {
        return ResponseEntity.ok(body);
    }

    public static ResponseEntity<String> error(String message) {
        return ResponseEntity.badRequest().body(message);
    }
}
