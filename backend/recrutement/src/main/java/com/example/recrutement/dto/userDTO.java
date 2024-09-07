package com.example.recrutement.dto;

import lombok.Data;
import lombok.ToString;

import java.sql.Date;


@Data
@ToString
public class userDTO {

    public int userID;
    public Date dateOfRegister;
    public String role;
    public String state;
    public String userName;
    public String userEmail;
    public String userPassword;
    public String confirmPassword;

}
