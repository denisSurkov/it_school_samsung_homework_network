package com.example.denis.networkpractice2.controllers;

import com.example.denis.networkpractice2.models.entities.User;

import java.util.ArrayList;

public interface AsyncResponse {
    void processFinish(ArrayList<User> users);
}