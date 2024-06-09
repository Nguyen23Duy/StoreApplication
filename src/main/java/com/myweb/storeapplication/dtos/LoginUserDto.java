package com.myweb.storeapplication.dtos;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginUserDto {

    private String userName;

    private String password;
}
