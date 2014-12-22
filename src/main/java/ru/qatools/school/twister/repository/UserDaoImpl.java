package ru.qatools.school.twister.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.qatools.school.twister.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by dima on 21.12.14.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public List<User> findAll() {
        return this.jdbcTemplate.query( "select * from USERS", new UserMapper() );
    }

    @Override
    public User findById( int id ) {
        return this.jdbcTemplate.queryForObject( "select * from USERS where id = ?", new Object[]{id}, new UserMapper() );
    }

    @Override
    public User findByName(String name) {
        return this.jdbcTemplate.queryForObject( "select * from USERS where name = ?", new Object[]{name}, new UserMapper() );
    }

    @Override
    public User persist(User user) {
        if ( user.getId() > 0 ) {
            update( user );
            return user;
        } else {
            return add( user );
        }
    }

    private void update(User user) {
        jdbcTemplate.update(
                "update users set name = ?, pass_hash = ?, avatar = ? where id = ?",
                user.getLogin(), user.getPassword(), user.getAvatar(), user.getId()
        );
    }

    private User add( final User user ) {
        final String INSERT_SQL = "insert into users (name, pass_hash, avatar) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] {"id", "pass_hash", "registered"});
                        ps.setString( 1, user.getLogin() );
                        ps.setString( 2, user.getPassword() );
                        ps.setBinaryStream(3, user.getAvatar());
                        return ps;
                    }
                },
                keyHolder
        );
        Integer newId = (Integer) keyHolder.getKeys().get("id");
        String passHash = (String) keyHolder.getKeys().get("pass_hash");
        Timestamp registered = (Timestamp) keyHolder.getKeys().get("registered");

        User registeredUser = new User();
        registeredUser.setId( newId );
        registeredUser.setLogin( user.getLogin() );
        registeredUser.setPassword( passHash );
        registeredUser.setAvatar( user.getAvatar() );
        registeredUser.setRegistered( registered );

        return registeredUser;
    }

    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private static final class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId( rs.getInt("id") );
            user.setLogin(rs.getString("name"));
            user.setPassword(rs.getString("pass_hash"));
            user.setRegistered(rs.getTimestamp("registered"));
            user.setAvatar( rs.getBlob("avatar").getBinaryStream() );
            return user;
        }
    }
}


