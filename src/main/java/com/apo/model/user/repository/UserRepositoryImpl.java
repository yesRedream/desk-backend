package com.apo.model.user.repository;

import com.apo.model.user.User;
import com.apo.model.user.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 23/06/2017.
 */
@Repository
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    private UserDAO dao;

    @Override
    public void add(User user) {
        dao.save(user);
    }

    @Override
    public void update(User user) {
        dao.save(user);
    }

    @Override
    public User findByID(long id) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findByName(String name) {
        return dao.getByName(name);
    }

    @Override
    public void remove(User user) {
        dao.remove(user);
    }

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }
}
