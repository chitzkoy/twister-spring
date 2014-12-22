package ru.qatools.school.twister.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.qatools.school.twister.model.User;
import ru.qatools.school.twister.repository.UserDao;

import java.util.List;

/**
 * Created by dima on 21.12.14.
 */
@Service
public class UserService {

    public List<User> findAll() {
        return this.userDao.findAll();
    }

    public User findById( int id ) {
        return this.userDao.findById( id );
    }

    public User findByName( String name ) {
        return this.userDao.findByName( name );
    }

    public void persist( final User user ) {
        this.userDao.persist(user);
    }

    @Autowired
    private UserDao userDao;
}
