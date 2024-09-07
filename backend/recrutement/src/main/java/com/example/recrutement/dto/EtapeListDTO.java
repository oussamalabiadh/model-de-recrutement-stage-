package com.example.recrutement.dto;

import com.example.recrutement.entity.EtapeRecrutement;

import java.util.List;

public class EtapeListDTO {
    private String state;
    private String message;
    private List<EtapeRecrutement> data;

    // Getters and Setters
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<EtapeRecrutement> getData() {
        return data;
    }

    public void setData(List<EtapeRecrutement> data) {
        this.data = data;
    }
}
