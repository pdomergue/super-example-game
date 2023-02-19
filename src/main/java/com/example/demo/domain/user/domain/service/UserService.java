package com.example.demo.domain.user.domain.service;

import com.example.demo.domain.user.domain.dao.UserRepository;
import com.example.demo.domain.user.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser(){
        return userRepository.findByUserName("pdomergue");
    }
}
