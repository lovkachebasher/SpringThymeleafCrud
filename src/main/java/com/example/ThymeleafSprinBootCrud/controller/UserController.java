package com.example.ThymeleafSprinBootCrud.controller;

import com.example.ThymeleafSprinBootCrud.entity.User;
import com.example.ThymeleafSprinBootCrud.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

public class UserController {
    //TODO -> написать тесты - интеграциионные(тест контейнер)

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/users", "/", "/list"})
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView("user-list");
        List<User> userList = userService.getAllUser();
        modelAndView.addObject("users", userList);
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addUserForm() {
        return new ModelAndView("add-user-form").addObject("user", new User());
    }

    @PostMapping("/save-user")
    public String addUser(User user) {
        if (user.getId() == null || user.getId().isEmpty() ) {
            userService.addUser(user);
        } else {
            try {
                userService.updateUser(user);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public ModelAndView editUser(@RequestParam("id") String id) {
        ModelAndView modelAndView = new ModelAndView("add-user-form");
        try {
            User user = userService.getUserById(id);
            modelAndView.addObject("user", user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return modelAndView;
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("id") String id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
