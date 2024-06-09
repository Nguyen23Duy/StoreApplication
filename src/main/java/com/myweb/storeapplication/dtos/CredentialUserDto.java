package com.myweb.storeapplication.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CredentialUserDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private String email;

    private String phoneNumber;

    private String address;
}
