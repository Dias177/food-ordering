package kz.epam.tcfp.foodordering.dao;

import kz.epam.tcfp.foodordering.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//TODO: Finish all CRUD methods and add other necessary methods
public class UserDao extends AbstractDao<Long, User> {

    private static final long USER_ROLE_ID = 2;
    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM user WHERE role_id = " + USER_ROLE_ID;
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM user WHERE id = ?";
    private static final String SQL_CREATE_USER = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_USER = "UPDATE user SET role_id = ?, email = ?, password = ?," +
            " first_name = ?, last_name = ?, phone_number = ?, birthday = ? WHERE id = ?";

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setRoleId(resultSet.getLong("role_id"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setBirthday(resultSet.getDate("birthday"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding all users", e);
        } finally {
            close(statement);
        }
        return users;
    }

    @Override
    public User findEntityById(Long id) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteEntityById(Long id) throws DaoException {
        return false;
    }

    @Override
    public boolean deleteEntity(User user) throws DaoException {
        return false;
    }

    @Override
    public boolean create(User user) throws DaoException {
        return false;
    }

    @Override
    public User update(User user) throws DaoException {
        return null;
    }
}
