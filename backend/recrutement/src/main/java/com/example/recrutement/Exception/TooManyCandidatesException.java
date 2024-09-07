package com.example.recrutement.Exception;

public class TooManyCandidatesException extends RuntimeException {
    public TooManyCandidatesException(String message) {
        super(message);
    }
}
