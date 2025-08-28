package com.farmit.kartoffelsoft_backend.exception;

public class AbteilungNichtGefunden extends RuntimeException {
    public AbteilungNichtGefunden(String message) {
        super(message);
    }
}
