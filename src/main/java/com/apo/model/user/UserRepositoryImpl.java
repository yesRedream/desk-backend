package com.apo.model.user;

import org.springframework.stereotype.Repository;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 23/06/2017.
 */
@Repository
public class UserRepositoryImpl implements UserRepository{

    @Override
    public boolean save(User user) {
        return false;
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
        User mockUser = new User("apohrebniak", "apo@gmail.com", "pass");
        mockUser.addRole("ROLE_USER");
        return mockUser;
    }

    @Override
    public boolean remove(User user) {
        return false;
    }

}
