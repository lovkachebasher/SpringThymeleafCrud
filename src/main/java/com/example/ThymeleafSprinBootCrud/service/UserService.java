package com.example.ThymeleafSprinBootCrud.service;

import com.example.ThymeleafSprinBootCrud.entity.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    User getUserById(String userId) ;

    void updateUser(User user) ;

    void deleteUser(String userId);

    List<User> getAllUser();

    void deleteAllUsers();
}
