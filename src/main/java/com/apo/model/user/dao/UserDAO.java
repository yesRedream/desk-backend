package com.apo.model.user.dao;

import com.apo.model.user.User;

import java.util.List;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 23/06/2017.
 */
public interface UserDAO {
    void save(User user);
    User getByName(String name);
    User getByID(String id);
    User getByEmail(String email);
    void remove(User user);
    List<User> getAll();
    boolean exists(User user);
}
