package com.example.recrutement.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class loginResponse {

    private String message;
    private Boolean status;
    private String Token;


}
