package com.pedro.repository;

import com.pedro.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    //第一次授权时加入用户至user表
    public void addUser(User user);

}
