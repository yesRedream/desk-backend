package com.apo.model.user.repository;

import com.apo.model.user.User;

import java.util.List;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 23/06/2017.
 */
public interface UserRepository {
    void add(User user);
    void update(User user);
    User findByID(String id);
    User findByEmail(String email);
    User findByName(String name);
    List<User> getAll();
    void  remove(User user);
    boolean exists(User user);

}
