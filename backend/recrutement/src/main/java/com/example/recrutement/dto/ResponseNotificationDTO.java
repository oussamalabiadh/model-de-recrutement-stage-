package com.example.recrutement.dto;

import com.example.recrutement.entity.Entretien;
import com.example.recrutement.entity.Notification;
import com.example.recrutement.entity.NotificationGlobale;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
public class ResponseNotificationDTO {
    private String state ;
    private String message ;
    private Object data ;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
