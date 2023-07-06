package com.example.ThymeleafSprinBootCrud.service.impl;

import com.example.ThymeleafSprinBootCrud.entity.User;
import com.example.ThymeleafSprinBootCrud.exception.NotExistException;
import com.example.ThymeleafSprinBootCrud.repository.UserRepository;
import com.example.ThymeleafSprinBootCrud.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Exceptions выкидывается в сервисе а в ExceptionHandler они перехватываются!
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
        logger.info("User successfully saved: " + user);
    }


    @Override
    public User getUserById(String userId) {
        return userRepository.findUserById(userId).orElseThrow(
                () -> new NotExistException("User with id: " + userId + " doesn't exist")
        );
    }

    @Override
    public void updateUser(User user) {
        userRepository.findUserById(user.getId()).orElseThrow(
                () -> new NotExistException("User with id: " + user.getId() + " doesn't exist")
        );
        userRepository.deleteById(user.getId());
        userRepository.save(user);
        logger.info("User successfully update: " + user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.findUserById(userId).orElseThrow(
                () -> new NotExistException("User not found with id: " + userId));
        userRepository.deleteById(userId);
        logger.info("User has been deleted with Id: " + userId);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
        logger.info("All users was deleted");
    }
}
