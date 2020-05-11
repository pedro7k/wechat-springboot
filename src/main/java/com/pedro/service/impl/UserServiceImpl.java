package com.pedro.service.impl;

import com.pedro.entity.User;
import com.pedro.repository.UserRepository;
import com.pedro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.addUser(user);
    }
}
