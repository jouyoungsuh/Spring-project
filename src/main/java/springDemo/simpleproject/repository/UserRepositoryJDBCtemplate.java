package springDemo.simpleproject.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import springDemo.simpleproject.domain.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserRepositoryJDBCtemplate implements UserRepositoryInterface{

    private final JdbcTemplate jdbcTemplate;
    public UserRepositoryJDBCtemplate(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public User save(User user) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        insert.withTableName("user").usingGeneratedKeyColumns("id");
        Map<String, Object> param = new HashMap<>();
        param.put("name", user.getName());
        Number key = insert.executeAndReturnKey(new MapSqlParameterSource(param));
        user.setId(key.intValue());
        return user;
    }

    @Override
    public Optional<User> findById(Integer id) {
        List<User> res = jdbcTemplate.query("select * from user where id = ?", rm(), id);
        return res.stream().findAny();
    }

    @Override
    public Optional<User> findByName(String name) {
        List<User> res = jdbcTemplate.query("select * from user where name = ?", rm(), name);
        return res.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from user", rm());
    }

    private RowMapper<User> rm() {
        return new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int num) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                return user;
            }
        };
    }
}