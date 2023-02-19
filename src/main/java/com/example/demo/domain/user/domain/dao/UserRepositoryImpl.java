package com.example.demo.domain.user.domain.dao;

import com.example.demo.domain.user.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class UserRepositoryImpl implements UserRepositoryCustom {

    @Autowired
    @Lazy
    private UserRepository userRepository;


}
