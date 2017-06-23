package com.apo.model.user;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 23/06/2017.
 */
public interface UserDAO {
    void save(User user);
    User getByName(String name);
    User getByID(long id);
    User getByEmail(String email);
    void remove(User user);
}
