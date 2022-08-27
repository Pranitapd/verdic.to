package com.ancient.verdicto.service;

import com.ancient.verdicto.model.User;
import org.springframework.stereotype.Service;

public interface UserService  {
    User getUserDetails(Long userId);

}
