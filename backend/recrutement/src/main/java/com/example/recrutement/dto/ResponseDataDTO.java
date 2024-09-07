package com.example.recrutement.dto;

import com.example.recrutement.entity.Candidat;
import com.example.recrutement.entity.Notification;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class  ResponseDataDTO {
    private String state ;
    private String message ;
    private List<Candidat> data ;


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

    public List<Candidat> getData() {
        return data;
    }

    public void setData(List<Candidat> data) {
        this.data = data;
    }
}
