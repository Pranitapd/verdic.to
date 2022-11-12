package com.ancient.verdicto.service;

import com.ancient.verdicto.dto.SignUpesponseDto;
import com.ancient.verdicto.dto.SignupDto;
import com.ancient.verdicto.exception.CustomException;
import com.ancient.verdicto.model.User;

import java.util.List;

public interface UserService  {
    User getUserDetails(Long userId);
    void deleteAll();
    List<User> getAllUsers();
    SignUpesponseDto signUp(SignupDto signupDto) throws CustomException;
}
