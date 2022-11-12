package com.ancient.verdicto.controller;

import com.ancient.verdicto.dto.SignUpesponseDto;
import com.ancient.verdicto.dto.SignupDto;
import com.ancient.verdicto.exception.CustomException;
import com.ancient.verdicto.model.User;
import com.ancient.verdicto.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @PostMapping("/signup")
    public SignUpesponseDto Signup(@RequestBody SignupDto signupDto) throws CustomException {
        return userService.signUp(signupDto);
    }

    @ResponseBody
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @ResponseBody
    @DeleteMapping("/user")
    public void deleteAllUsers(){
        userService.deleteAll();
    }
}
