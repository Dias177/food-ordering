package kz.epam.tcfp.foodordering.logic;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.dao.EntityTransaction;
import kz.epam.tcfp.foodordering.dao.FoodDao;
import kz.epam.tcfp.foodordering.entity.Food;

import java.util.List;

public class FoodLogic {

    private static final FoodDao foodDao = new FoodDao();
    private static final EntityTransaction transaction = new EntityTransaction();

    private FoodLogic() {
        throw new IllegalStateException("Logic utility class");
    }

    public static boolean nameExists(String foodName) throws DaoException {
        Food food;
        transaction.init(foodDao);
        try {
            food = foodDao.findFoodByName(foodName);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return food.getName() != null;
    }

    public static void add(long categoryId, String name, String description, Double price) throws DaoException {
        Food food = new Food(categoryId, name, description, price);
        transaction.initTransaction(foodDao);
        try {
            foodDao.create(food);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new DaoException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    public static List<Food> getAllItems() throws DaoException {
        List<Food> foodItems;
        transaction.init(foodDao);
        try {
            foodItems = foodDao.findAll();
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return foodItems;
    }

    public static Food getFood(long id) throws DaoException {
        Food food;
        transaction.init(foodDao);
        try {
            food = foodDao.findEntityById(id);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return food;
    }

    public static Food getFood(String name) throws DaoException {
        Food food;
        transaction.init(foodDao);
        try {
            food = foodDao.findFoodByName(name);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return food;
    }

    public static boolean edit(long id, String name, long categoryId, String description, double price)
            throws DaoException {
        Food food = new Food(categoryId, name, description, price);
        food.setId(id);
        transaction.initTransaction(foodDao);
        int rows;
        try {
            rows = foodDao.update(food);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new DaoException(e);
        } finally {
            transaction.endTransaction();
        }
        return rows != 0;
    }

    public static List<Food> getFoodByCategory(long foodCategoryId) throws DaoException {
        List<Food> foodList;
        transaction.init(foodDao);
        try {
            foodList = foodDao.findFoodByCategory(foodCategoryId);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return foodList;
    }

    public static boolean remove(long id) throws DaoException {
        boolean isRemoved;
        transaction.initTransaction(foodDao);
        try {
            isRemoved = foodDao.deleteEntityById(id);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new DaoException(e);
        } finally {
            transaction.endTransaction();
        }
        return isRemoved;
    }
}
