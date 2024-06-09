package com.myweb.storeapplication.mappers;

import com.myweb.storeapplication.dtos.CredentialUserDto;
import com.myweb.storeapplication.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    CredentialUserDto toCredentialUserDto(User user);

    @Mapping(target = "password", ignore = true)
    default User toUser(CredentialUserDto credentialUserDto)
    {
        User user = new User();
        user.setUserName(credentialUserDto.getUserName());
        user.setPhoneNumber(credentialUserDto.getPhoneNumber());
        user.setName(credentialUserDto.getFirstName() + credentialUserDto.getLastName());
        user.setEmail(credentialUserDto.getEmail());
        return user;
    }

}
