package com.apo.model.user.dao;

import com.apo.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 23/06/2017.
 */
public class UserDAOMongo implements UserDAO{

    @Autowired
    private MongoOperations operations;

    @Override
    public void save(User user) {
        operations.save(user);
    }

    @Override
    public User getByName(String name) {
        User user = null;
        user = operations.findOne(Query.query(Criteria.where("username").is(name)), User.class);
        return user;
    }

    @Override
    public User getByID(long id) {
        User user = null;
        user = operations.findOne(Query.query(Criteria.where("_id").is(id)), User.class);
        return user;
    }

    @Override
    public User getByEmail(String email) {
        User user = null;
        user = operations.findOne(Query.query(Criteria.where("email").is(email)), User.class);
        return user;
    }

    @Override
    public void remove(User user) {
        operations.remove(user);
    }

    @Override
    public List<User> getAll() {
        return operations.findAll(User.class);
    }
}
