package com.example.recrutement.dto;

import com.example.recrutement.entity.EtapeRecrutement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class EtapeDTO<T> {
    private String state;
    private String message;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}