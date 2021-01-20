package kz.epam.tcfp.foodordering.dao;

import kz.epam.tcfp.foodordering.entity.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RoleDao extends AbstractDao<Long, Role> {

    private static final String SQL_SELECT_ROLE_BY_ID = "SELECT * FROM role WHERE id = ?";
    private static final String COLUMN_LABEL_ID = "id";
    private static final String COLUMN_LABEL_NAME = "name";

    @Override
    public List<Role> findAll() throws DaoException {
        return null;
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
        return false;
    }

    @Override
    public boolean deleteEntity(Role role) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Role role) throws DaoException {
        return false;
    }

    @Override
    public int update(Role role) throws DaoException {
        return 0;
    }
}
