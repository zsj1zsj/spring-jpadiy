package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Helloworld {
    @Autowired
    UserRepository repository;

    @GetMapping("/users")
    public List<User> users() {
        List<User> result = repository.findAll();
        System.out.println("查到的数据：" + result.size());
        return result;
    }

}
