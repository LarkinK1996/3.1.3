package com.example.bootstrap3_1_3.dao;


import com.example.bootstrap3_1_3.model.User;

import java.util.List;

public interface AdminDao {
    void add(User user);

    void delete(int id);

    User getUser(int id);

    List<User> getAllUsers();

    void updateUser(int id, User newUser);

    User findByUsername(String username);
}
