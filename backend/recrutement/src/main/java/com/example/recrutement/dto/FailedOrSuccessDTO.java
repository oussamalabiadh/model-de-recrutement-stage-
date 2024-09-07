package com.example.recrutement.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class FailedOrSuccessDTO {

    private boolean status;

    public boolean getstatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
