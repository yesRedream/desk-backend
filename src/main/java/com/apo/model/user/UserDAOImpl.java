package com.apo.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 23/06/2017.
 */
public class UserDAOImpl implements UserDAO{
    private static final String COLLECTION = "users";

    @Autowired
    private MongoOperations operations;

    @Override
    public void save(User user) {
        operations.save(user, COLLECTION);
    }

    @Override
    public User getByName(String name) {
        User user = null;
        user = operations.findOne(Query.query(Criteria.where("username").is(name)), User.class, COLLECTION);
        return user;
    }

    @Override
    public User getByID(long id) {
        User user = null;
        user = operations.findOne(Query.query(Criteria.where("id").is(id)), User.class, COLLECTION);
        return user;
    }

    @Override
    public User getByEmail(String email) {
        User user = null;
        user = operations.findOne(Query.query(Criteria.where("email").is(email)), User.class, COLLECTION);
        return user;
    }

    @Override
    public void remove(User user) {
        operations.remove(user, COLLECTION);
    }
}
