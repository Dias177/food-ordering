package kz.epam.tcfp.foodordering.dao;

import kz.epam.tcfp.foodordering.entity.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDao extends AbstractDao<Long, Role> {

    private static final String COLUMN_LABEL_ID = "id";
    private static final String COLUMN_LABEL_NAME = "name";
    private static final String SQL_SELECT_ROLE_BY_ID = "SELECT * FROM role WHERE id = ?";
    private static final String SQL_SELECT_ALL_ROLES = "SELECT * FROM role ORDER BY id";
    private static final String SQL_DELETE_ROLE_BY_ID = "DELETE FROM role WHERE id = ?";
    private static final String SQL_UPDATE_ROLE = "UPDATE role SET name = ? WHERE id = ?";
    private static final String SQL_CREATE_ROLE = "INSERT INTO role (name) VALUES (?)";
    private static final String SQL_SELECT_ROLE_BY_NAME = "SELECT * FROM role WHERE name = ?";

    public Role findRoleByName(String name) throws DaoException {
        Role role = new Role();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ROLE_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                role.setId(resultSet.getLong(COLUMN_LABEL_ID));
                role.setName(resultSet.getString(COLUMN_LABEL_NAME));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding role by name", e);
        } finally {
            close(statement);
        }
        return role;
    }

    @Override
    public List<Role> findAll() throws DaoException {
        List<Role> roles = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ROLES);
            while (resultSet.next()) {
                Role role = new Role(resultSet.getString(COLUMN_LABEL_NAME));
                role.setId(resultSet.getLong(COLUMN_LABEL_ID));
                roles.add(role);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding all roles", e);
        } finally {
            close(statement);
        }
        return roles;
    }

    @Override
    public Role findEntityById(Long id) throws DaoException {
        Role role = new Role();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ROLE_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                 role.setId(resultSet.getLong(COLUMN_LABEL_ID));
                 role.setName(resultSet.getString(COLUMN_LABEL_NAME));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding role by id", e);
        } finally {
            close(statement);
        }
        return role;
    }

    @Override
    public boolean deleteEntityById(Long id) throws DaoException {
        int rows;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_ROLE_BY_ID);
            statement.setLong(1, id);
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in deleting role by id", e);
        } finally {
            close(statement);
        }
        return rows > 0;
    }

    @Override
    public boolean deleteEntity(Role role) throws DaoException {
        int rows;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_ROLE_BY_ID);
            long id = role.getId();
            statement.setLong(1, id);
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in deleting role", e);
        } finally {
            close(statement);
        }
        return rows > 0;
    }

    @Override
    public boolean create(Role role) throws DaoException {
        Role r = findRoleByName(role.getName());
        if (r.getName() != null && r.getId() != 0) {
            return false;
        }
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_ROLE);
            statement.setString(1, role.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in creating role", e);
        } finally {
            close(statement);
        }
        return true;
    }

    @Override
    public int update(Role role) throws DaoException {
        int rows;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_UPDATE_ROLE);
            statement.setString(1, role.getName());
            statement.setLong(2, role.getId());
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in updating role", e);
        } finally {
            close(statement);
        }
        return rows;
    }
}
