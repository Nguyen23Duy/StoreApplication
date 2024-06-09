package com.myweb.storeapplication.services;

import com.myweb.storeapplication.dtos.CredentialUserDto;
import com.myweb.storeapplication.entities.User;
import com.myweb.storeapplication.exceptions.AppException;
import com.myweb.storeapplication.mappers.UserMapper;
import com.myweb.storeapplication.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public CredentialUserDto registerUser(CredentialUserDto credentialUserDto)
    {
        User savedUser = new User();
        Optional<User> userOptional = userRepository.findByUserName(credentialUserDto.getUserName());
        if (userOptional.isPresent())
        {
            throw new AppException("User is already exist, try another user", HttpStatus.BAD_REQUEST);
        }

        User registerUser = userMapper.toUser(credentialUserDto);
        registerUser.setPassword(passwordEncoder.encode(CharBuffer.wrap(credentialUserDto.getPassword())));
        savedUser = userRepository.save(registerUser);
        return userMapper.toCredentialUserDto(savedUser);
    }

    public CredentialUserDto login(CredentialUserDto login)
    {
        User user = userRepository.findByUserName(login.getUserName()).
                orElseThrow( () -> new AppException("User not exist, try another user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(login.getPassword()), user.getPassword()))
        {
            return userMapper.toCredentialUserDto(user);
        }
        else
        {
            throw new AppException("Password invalid", HttpStatus.BAD_REQUEST);
        }
    }
}
