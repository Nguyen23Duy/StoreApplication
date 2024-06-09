package com.myweb.storeapplication.Controller;

import com.myweb.storeapplication.model.User;
import com.myweb.storeapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping(path = "/add")
    @ResponseBody
    public User addNewUser(User user)
    {
        if (Objects.isNull(user))
        {
            System.out.printf("No user input");
        }
        else
        {
            userRepository.save(user);
        }
        return user;
    }

    @GetMapping(path = "/readAll")
    public Iterator<User> readAllUser()
    {
        return userRepository.findAll().iterator();
    }

}
