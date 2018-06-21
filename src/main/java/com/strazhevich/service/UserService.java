package com.strazhevich.service;


import com.strazhevich.entity.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}
