package kz.epam.tcfp.foodordering.logic;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.dao.EntityTransaction;
import kz.epam.tcfp.foodordering.dao.FoodCategoryDao;
import kz.epam.tcfp.foodordering.entity.FoodCategory;

public class FoodCategoryLogic {

    private FoodCategoryLogic() {
        throw new IllegalStateException("Logic utility class");
    }

    public static void add(String categoryName) throws DaoException {
        FoodCategory foodCategory = new FoodCategory(categoryName);
        FoodCategoryDao foodCategoryDao = new FoodCategoryDao();
        EntityTransaction transaction = new EntityTransaction();
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
        FoodCategoryDao foodCategoryDao = new FoodCategoryDao();
        EntityTransaction transaction = new EntityTransaction();
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
}
