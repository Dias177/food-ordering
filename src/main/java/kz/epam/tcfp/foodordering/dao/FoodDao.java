package kz.epam.tcfp.foodordering.dao;

import kz.epam.tcfp.foodordering.entity.Food;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FoodDao extends AbstractDao<Long, Food> {

    private static final String COLUMN_LABEL_ID = "id";
    private static final String COLUMN_LABEL_FOOD_CATEGORY_ID = "food_category_id";
    private static final String COLUMN_LABEL_NAME = "name";
    private static final String COLUMN_LABEL_DESCRIPTION = "description";
    private static final String COLUMN_LABEL_PRICE = "price";
    private static final String SQL_SELECT_FOOD_BY_NAME = "SELECT * FROM food WHERE name = ?";
    private static final String SQL_CREATE_FOOD = "INSERT INTO food (food_category_id, name, description, price) " +
            "VALUES (?, ?, ?, ?)";
    private static final String SQL_SELECT_ALL_FOOD = "SELECT * FROM food";
    private static final String SQL_SELECT_FOOD_BY_ID = "SELECT * FROM food WHERE id = ?";

    public Food findFoodByName(String name) throws DaoException {
        Food food = new Food();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_FOOD_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                food.setId(resultSet.getLong(COLUMN_LABEL_ID));
                food.setFoodCategoryId(resultSet.getLong(COLUMN_LABEL_FOOD_CATEGORY_ID));
                food.setName(resultSet.getString(COLUMN_LABEL_NAME));
                food.setDescription(resultSet.getString(COLUMN_LABEL_DESCRIPTION));
                food.setPrice(resultSet.getDouble(COLUMN_LABEL_PRICE));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding food by name", e);
        } finally {
            close(statement);
        }
        return food;
    }
    @Override
    public List<Food> findAll() throws DaoException {
        List<Food> foodItems = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FOOD);
            while (resultSet.next()) {
                Food food = new Food(resultSet.getLong(COLUMN_LABEL_FOOD_CATEGORY_ID),
                        resultSet.getString(COLUMN_LABEL_NAME), resultSet.getString(COLUMN_LABEL_DESCRIPTION),
                        resultSet.getDouble(COLUMN_LABEL_PRICE));
                food.setId(resultSet.getLong(COLUMN_LABEL_ID));
                foodItems.add(food);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding all food", e);
        } finally {
            close(statement);
        }
        return foodItems;
    }

    @Override
    public Food findEntityById(Long id) throws DaoException {
        Food food = new Food();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_FOOD_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                food.setId(resultSet.getLong(COLUMN_LABEL_ID));
                food.setFoodCategoryId(resultSet.getLong(COLUMN_LABEL_FOOD_CATEGORY_ID));
                food.setName(resultSet.getString(COLUMN_LABEL_NAME));
                food.setDescription(resultSet.getString(COLUMN_LABEL_DESCRIPTION));
                food.setPrice(resultSet.getDouble(COLUMN_LABEL_PRICE));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in finding food by id", e);
        } finally {
            close(statement);
        }
        return food;
    }

    @Override
    public boolean deleteEntityById(Long id) throws DaoException {
        return false;
    }

    @Override
    public boolean deleteEntity(Food food) throws DaoException {
        return false;
    }

    @Override
    public boolean create(Food food) throws DaoException {
        Food f = findFoodByName(food.getName());
        if (f.getName() != null) {
            return false;
        }
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_CREATE_FOOD);
            statement.setLong(1, food.getFoodCategoryId());
            statement.setString(2, food.getName());
            statement.setString(3, food.getDescription());
            statement.setDouble(4, food.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in creating food", e);
        } finally {
            close(statement);
        }
        return true;
    }

    @Override
    public int update(Food food) throws DaoException {
        return 0;
    }
}
