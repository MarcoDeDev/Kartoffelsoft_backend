package com.farmit.kartoffelsoft_backend.exception;

public class LieferantNichtGefunden extends RuntimeException {
    public LieferantNichtGefunden(String message) {
        super(message);
    }
}
