package kz.epam.tcfp.foodordering.dao;

import kz.epam.tcfp.foodordering.entity.FoodCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FoodCategoryDao extends AbstractDao<Long, FoodCategory> {

    private static final String COLUMN_LABEL_ID = "id";
    private static final String COLUMN_LABEL_NAME = "name";
    private static final String SQL_SELECT_FOOD_CATEGORY_BY_NAME = "SELECT * FROM food_category WHERE name = ?";
    private static final String SQL_CREATE_FOOD_CATEGORY = "INSERT INTO food_category (name) VALUES (?)";
    private static final String SQL_SELECT_ALL = "SELECT * FROM food_category ORDER BY id";
    private static final String SQL_SELECT_FOOD_CATEGORY_BY_ID = "SELECT * FROM food_category WHERE id = ?";
    private static final String SQL_DELETE_FOOD_CATEGORY_BY_ID = "DELETE FROM food_category WHERE id = ?";

    public FoodCategory findFoodCategoryByName(String categoryName) throws DaoException {
        FoodCategory foodCategory = new FoodCategory();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_FOOD_CATEGORY_BY_NAME);
            statement.setString(1, categoryName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                foodCategory.setId(resultSet.getLong(COLUMN_LABEL_ID));
                foodCategory.setName(resultSet.getString(COLUMN_LABEL_NAME));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding food category by name", e);
        } finally {
            close(statement);
        }
        return foodCategory;
    }
    @Override
    public List<FoodCategory> findAll() throws DaoException {
        List<FoodCategory> foodCategories = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                FoodCategory foodCategory = new FoodCategory(resultSet.getString(COLUMN_LABEL_NAME));
                foodCategory.setId(resultSet.getLong(COLUMN_LABEL_ID));
                foodCategories.add(foodCategory);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding all food categories", e);
        } finally {
            close(statement);
        }
        return foodCategories;
    }

    @Override
    public FoodCategory findEntityById(Long id) throws DaoException {
        FoodCategory foodCategory = new FoodCategory();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_FOOD_CATEGORY_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                foodCategory.setName(resultSet.getString(COLUMN_LABEL_NAME));
                foodCategory.setId(resultSet.getLong(COLUMN_LABEL_ID));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding food category by id", e);
        } finally {
            close(statement);
        }
        return foodCategory;
    }

    @Override
    public boolean deleteEntityById(Long id) throws DaoException {
        int rows;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_DELETE_FOOD_CATEGORY_BY_ID);
            statement.setLong(1, id);
            rows = statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in deleting food category by id", e);
        } finally {
            close(statement);
        }
        return rows > 0;
    }

    @Override
    public boolean deleteEntity(FoodCategory foodCategory) throws DaoException {
        return false;
    }

    @Override
    public boolean create(FoodCategory foodCategory) throws DaoException {
        FoodCategory fc = findFoodCategoryByName(foodCategory.getName());
        if (fc.getName() != null) {
            return false;
        }
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_FOOD_CATEGORY);
            statement.setString(1, foodCategory.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in creating food category", e);
        } finally {
            close(statement);
        }
        return true;
    }

    @Override
    public int update(FoodCategory foodCategory) throws DaoException {
        return 0;
    }
}
