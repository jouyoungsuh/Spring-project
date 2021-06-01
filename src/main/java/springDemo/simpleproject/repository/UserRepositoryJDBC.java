package springDemo.simpleproject.repository;

import org.springframework.jdbc.datasource.DataSourceUtils;
import springDemo.simpleproject.domain.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserRepositoryJDBC implements UserRepositoryInterface {
    private final DataSource dataSource;
    public UserRepositoryJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User save(User user) {
        String sql = "insert into user(name) values(?)";

        Connection conn = null;
        PreparedStatement ppst = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ppst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ppst.setString(1, user.getName());
            ppst.executeUpdate();
            rs = ppst.getGeneratedKeys();

            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
            else {
                throw new SQLException("Cannot find ID");
            }
            return user;
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
        }
        finally {
            close(conn, ppst, rs);
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        String sql = "select * from user where id = ?";

        Connection conn = null;
        PreparedStatement ppst = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ppst = conn.prepareStatement(sql);
            ppst.setInt(1, id);
            rs = ppst.executeQuery();

            if(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                return Optional.of(user);
            }
            else {
                return Optional.empty();
            }
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
        }
        finally {
            close(conn, ppst, rs);
        }
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        Connection conn = null;
        PreparedStatement ppst = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ppst = conn.prepareStatement(sql);
            rs = ppst.executeQuery();
            List<User> users = new ArrayList<>();
            while(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                users.add(user);
            }
            return users;
        }

        catch (Exception e) {
            throw new IllegalStateException(e);
        }
        finally {
            close(conn, ppst, rs);
        }
    }

    @Override
    public Optional<User> findByName(String name) {
        String sql = "select * from user where name = ?";
        Connection conn = null;
        PreparedStatement ppst = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            ppst = conn.prepareStatement(sql);
            ppst.setString(1, name);
            rs = ppst.executeQuery();

            if(rs.next())
            {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                return Optional.of(user);
            }
            return Optional.empty();
        }

        catch (Exception e) {
            throw new IllegalStateException(e);
        }
        finally {
            close(conn, ppst, rs);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
    {
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}

