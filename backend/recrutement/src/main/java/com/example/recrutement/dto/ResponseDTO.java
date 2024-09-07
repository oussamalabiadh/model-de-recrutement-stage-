package com.example.recrutement.dto;

import com.example.recrutement.entity.Entretien;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private String state ;
    private String message ;
    private List<Entretien> data ;
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

    public List<Entretien> getData() {
        return data;
    }

    public void setData(List<Entretien> data) {
        this.data = data;
    }
}
