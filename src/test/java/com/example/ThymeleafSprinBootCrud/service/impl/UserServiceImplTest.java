package com.example.ThymeleafSprinBootCrud.service.impl;

import com.example.ThymeleafSprinBootCrud.entity.User;
import com.example.ThymeleafSprinBootCrud.repository.UserRepository;
import com.example.ThymeleafSprinBootCrud.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private UserRepository userRepository;
    private UserService userService;
    private final static User USER = new User("1", "vasya", 15, "1231890");
    private final static User USER2 = new User("2", "igor", 16, "0329842");
    private final static User USER3 = new User("3", "pimp", 17, "1827931");


    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    void addUser() {
        when(userRepository.save(USER)).thenReturn(USER);
        userService.addUser(USER);
        verify(userRepository, times(1)).save(USER);
    }

    @Test
    void getUserById() {
        when(userRepository.findUserById(USER.getId())).thenReturn(Optional.of(USER));
        User userById = userService.getUserById("1");
        Assertions.assertEquals(USER, userById);

    }

    @Test
    void updateUser() {
        when(userRepository.findUserById(USER.getId())).thenReturn(Optional.of(USER));
        userService.updateUser(USER);
        verify(userRepository, times(1)).findUserById(USER.getId());
        verify(userRepository, times(1)).deleteById(USER.getId());
        verify(userRepository, times(1)).save(USER);
    }

    @Test
    void deleteUser() {
        when(userRepository.findUserById(USER.getId())).thenReturn(Optional.of(USER));
        userService.deleteUser(USER.getId());
        verify(userRepository, times(1)).deleteById(USER.getId());
    }

    @Test
    void getAllUser() {
        List<User> userList = new ArrayList<>();
        userList.add(USER);
        userList.add(USER2);
        userList.add(USER3);
        when(userRepository.findAll()).thenReturn(userList);

        List<User> userListFromService = userService.getAllUser();
        Assertions.assertEquals(userList, userListFromService);
    }

    @Test
    void deleteAllUsers() {
        userService.deleteAllUsers();
        verify(userRepository, times(1)).deleteAll();
    }
}