package com.myweb.storeapplication.controllers;

import com.myweb.storeapplication.dtos.CredentialUserDto;
import com.myweb.storeapplication.dtos.LoginUserDto;
import com.myweb.storeapplication.exceptions.AppException;
import com.myweb.storeapplication.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class UserController {

    @Autowired
    private UserServices userServices;


    @PostMapping("/register")
    public ResponseEntity<CredentialUserDto> register(@RequestBody CredentialUserDto user) {
        CredentialUserDto createdUser = userServices.registerUser(user);
        ResponseEntity<CredentialUserDto> credentialUserDtoResponseEntity = ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }


    @PostMapping("/login")
    public ResponseEntity<CredentialUserDto> login(@RequestBody CredentialUserDto loginUser)
    {
        if (Objects.nonNull(loginUser))
        {
            CredentialUserDto userDto = userServices.login(loginUser);
            return ResponseEntity.ok(userDto);
        }
        else
        {
            throw new AppException("information is empty, please full fill it", HttpStatus.BAD_REQUEST);
        }

    }



}
