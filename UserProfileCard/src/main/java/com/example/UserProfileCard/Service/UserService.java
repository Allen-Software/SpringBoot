package com.example.UserProfileCard.Service;

import org.springframework.stereotype.Service;
import com.example.UserProfileCard.Model.User;

@Service
public class UserService {
    public User getMockUser(){
        User user = new User();
        user.setUsername("Allen");
        user.setRole("ADMIN");
        user.setAccountLocked(false);
        return user;
    }
}