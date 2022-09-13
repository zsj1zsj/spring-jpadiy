package com.example.demo.controllers;

import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloworldTest {
    @Autowired
    UserRepository repository;

    @Test
    public void users() {
        System.out.println(repository.findAll());

    }
}