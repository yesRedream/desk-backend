package com.apo.model.user;

import com.apo.db.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 23/06/2017.
 */
@Repository
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    private UserDAO dao;

    @Override
    public void  save(User user) {
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

}
