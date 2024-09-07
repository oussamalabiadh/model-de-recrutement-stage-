package com.example.recrutement.Exception;

public class CandidatNotFoundException extends RuntimeException {
    public CandidatNotFoundException(String message) {
        super(message);
    }
}
