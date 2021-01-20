package kz.epam.tcfp.foodordering.dao;

import kz.epam.tcfp.foodordering.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<Long, User> {

    private static final long USER_ROLE_ID = 2;
    private static final String COLUMN_LABEL_ID = "id";
    private static final String COLUMN_LABEL_ROLE_ID = "role_id";
    private static final String COLUMN_LABEL_EMAIL = "email";
    private static final String COLUMN_LABEL_FIRST_NAME = "first_name";
    private static final String COLUMN_LABEL_LAST_NAME = "last_name";
    private static final String COLUMN_LABEL_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_LABEL_BIRTHDAY = "birthday";
    private static final String SQL_SELECT_USER_BY_EMAIL_AND_PASSWORD = "SELECT * FROM user WHERE email = ? AND " +
            "password = ?";
    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM user WHERE role_id = " + USER_ROLE_ID;
    private static final String SQL_SELECT_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM user WHERE id = ?";
    private static final String SQL_CREATE_USER = "INSERT INTO user (role_id, email, " +
            "password, first_name, last_name, phone_number, birthday) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_USER = "UPDATE user SET role_id = ?, email = ?, password = ?," +
            " first_name = ?, last_name = ?, phone_number = ?, birthday = ? WHERE id = ?";
    private static final String SQL_SELECT_USER_BY_EMAIL = "SELECT * FROM user WHERE email = ?";

    public User findUserByEmailAndPassword(String email, String password) throws DaoException {
        User user = new User();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL_AND_PASSWORD);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(COLUMN_LABEL_ID));
                user.setRoleId(resultSet.getLong(COLUMN_LABEL_ROLE_ID));
                user.setEmail(resultSet.getString(COLUMN_LABEL_EMAIL));
                user.setFirstName(resultSet.getString(COLUMN_LABEL_FIRST_NAME));
                user.setLastName(resultSet.getString(COLUMN_LABEL_LAST_NAME));
                user.setPhoneNumber(resultSet.getString(COLUMN_LABEL_PHONE_NUMBER));
                user.setBirthday(resultSet.getDate(COLUMN_LABEL_BIRTHDAY));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding user by email and password", e);
        } finally {
            close(statement);
        }
        return user;
    }

    public User findUserByEmail(String email) throws DaoException {
        User user = new User();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_EMAIL);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(COLUMN_LABEL_ID));
                user.setRoleId(resultSet.getLong(COLUMN_LABEL_ROLE_ID));
                user.setEmail(resultSet.getString(COLUMN_LABEL_EMAIL));
                user.setFirstName(resultSet.getString(COLUMN_LABEL_FIRST_NAME));
                user.setLastName(resultSet.getString(COLUMN_LABEL_LAST_NAME));
                user.setPhoneNumber(resultSet.getString(COLUMN_LABEL_PHONE_NUMBER));
                user.setBirthday(resultSet.getDate(COLUMN_LABEL_BIRTHDAY));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding user by email", e);
        } finally {
            close(statement);
        }
        return user;
    }

    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(COLUMN_LABEL_ID));
                user.setRoleId(resultSet.getLong(COLUMN_LABEL_ROLE_ID));
                user.setEmail(resultSet.getString(COLUMN_LABEL_EMAIL));
                user.setFirstName(resultSet.getString(COLUMN_LABEL_FIRST_NAME));
                user.setLastName(resultSet.getString(COLUMN_LABEL_LAST_NAME));
                user.setPhoneNumber(resultSet.getString(COLUMN_LABEL_PHONE_NUMBER));
                user.setBirthday(resultSet.getDate(COLUMN_LABEL_BIRTHDAY));
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
        User user = new User();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getLong(COLUMN_LABEL_ID));
                user.setRoleId(resultSet.getLong(COLUMN_LABEL_ROLE_ID));
                user.setEmail(resultSet.getString(COLUMN_LABEL_EMAIL));
                user.setFirstName(resultSet.getString(COLUMN_LABEL_FIRST_NAME));
                user.setLastName(resultSet.getString(COLUMN_LABEL_LAST_NAME));
                user.setPhoneNumber(resultSet.getString(COLUMN_LABEL_PHONE_NUMBER));
                user.setBirthday(resultSet.getDate(COLUMN_LABEL_BIRTHDAY));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding user by id", e);
        } finally {
            close(statement);
        }
        return user;
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
        User u = findUserByEmail(user.getEmail());
        if (u.getEmail() != null) {
            return false;
        }
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_USER);
            statement.setLong(1, USER_ROLE_ID);
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getPhoneNumber());
            statement.setDate(7, user.getBirthday());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in creating a new user", e);
        } finally {
            close(statement);
        }
        return true;
    }

    @Override
    public int update(User user) throws DaoException {
        return 0;
    }
}
