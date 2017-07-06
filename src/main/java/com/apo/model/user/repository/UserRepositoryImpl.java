package com.apo.model.user.repository;

import com.apo.error.UserExistsException;
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
    public void add(User user) throws UserExistsException {
        if (exists(user)) {
            throw new UserExistsException();
        } else {
            dao.save(user);
        }
    }

    @Override
    public void update(User user) {
        dao.save(user);
    }

    @Override
    public User findByID(String id) {
        return dao.getByID(id);
    }

    @Override
    public User findByEmail(String email) {
        return dao.getByEmail(email);
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

    @Override
    public boolean exists(User user) {
        return dao.exists(user);
    }
}
