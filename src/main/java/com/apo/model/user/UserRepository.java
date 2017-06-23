package com.apo.model.user;

/**
 * Created by Andrii Pohrebniak andrii.pohrebniak@gmail.com on 23/06/2017.
 */
public interface UserRepository {
    boolean save(User user);
    User findByID(long id);
    User findByEmail(String email);
    User findByName(String name);
    boolean remove(User user);

}
