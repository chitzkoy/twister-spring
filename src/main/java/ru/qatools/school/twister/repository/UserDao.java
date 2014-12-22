package ru.qatools.school.twister.repository;

import ru.qatools.school.twister.model.User;

import java.util.List;

/**
 * Created by dima on 21.12.14.
 */
public interface UserDao {

    List<User> findAll();

    User findById( int id );

    User findByName(String name);

    User persist(User user);

}
