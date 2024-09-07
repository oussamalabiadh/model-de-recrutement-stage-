package com.example.recrutement.service;

import com.example.recrutement.dto.loginDTO;
import com.example.recrutement.response.loginResponse;
public interface UserService {

    loginResponse loginUser(loginDTO loginDTO);


}
