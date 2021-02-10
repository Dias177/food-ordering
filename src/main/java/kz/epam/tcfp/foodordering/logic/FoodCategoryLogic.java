package kz.epam.tcfp.foodordering.logic;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.dao.EntityTransaction;
import kz.epam.tcfp.foodordering.dao.FoodCategoryDao;
import kz.epam.tcfp.foodordering.entity.FoodCategory;

import java.util.List;

public class FoodCategoryLogic {

    private static final FoodCategoryDao foodCategoryDao = new FoodCategoryDao();
    private static final EntityTransaction transaction = new EntityTransaction();

    private FoodCategoryLogic() {
        throw new IllegalStateException("Logic utility class");
    }

    public static void add(String categoryName) throws DaoException {
        FoodCategory foodCategory = new FoodCategory(categoryName);
        transaction.initTransaction(foodCategoryDao);
        try {
            foodCategoryDao.create(foodCategory);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new DaoException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    public static boolean categoryExists(String categoryName) throws DaoException {
        FoodCategory foodCategory;
        transaction.init(foodCategoryDao);
        try {
            foodCategory = foodCategoryDao.findFoodCategoryByName(categoryName);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return foodCategory.getName() != null;
    }

    public static List<FoodCategory> getAll() throws DaoException {
        List<FoodCategory> foodCategories;
        transaction.init(foodCategoryDao);
        try {
            foodCategories = foodCategoryDao.findAll();
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return foodCategories;
    }

    public static FoodCategory getCategoryById(long categoryId) throws DaoException {
        FoodCategory foodCategory;
        transaction.init(foodCategoryDao);
        try {
            foodCategory = foodCategoryDao.findEntityById(categoryId);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return foodCategory;
    }
}
