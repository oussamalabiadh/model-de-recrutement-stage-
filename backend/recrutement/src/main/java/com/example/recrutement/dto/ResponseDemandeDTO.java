package com.example.recrutement.dto;

import com.example.recrutement.entity.Demande;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
public class ResponseDemandeDTO {
    private String state ;
    private String message ;
    private List<Demande> data ;



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

    public List<Demande> getData() {
        return data;
    }

    public void setData(List<Demande> data) {
        this.data = data;
    }
}
