package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.FoodCategory;
import kz.epam.tcfp.foodordering.logic.FoodCategoryLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class EditFoodCategoryCommand implements ActionCommand {

    private static final String PATH_PAGE_EDIT_FOOD_CATEGORY = "path.page.edit.food.category";
    private static final String PARAM_NAME_FOOD_CATEGORY_ID = "foodCategoryId";
    private static final String PARAM_NAME_FOOD_CATEGORY_NAME = "foodCategoryName";
    private static final String IS_SUCCESS_EDIT_FOOD_CATEGORY = "isSuccessEditFoodCategory";
    private static final String IS_ERROR_EDIT_FOOD_CATEGORY = "isErrorEditFoodCategory";
    private static final boolean SUCCESS = true;
    private static final boolean ERROR = true;

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT_FOOD_CATEGORY);
        long foodCategoryId = Long.parseLong(req.getParameter(PARAM_NAME_FOOD_CATEGORY_ID));
        String foodCategoryName = req.getParameter(PARAM_NAME_FOOD_CATEGORY_NAME);
        FoodCategory foodCategory = FoodCategoryLogic.getCategoryByName(foodCategoryName);
        if (foodCategory.getName() != null && foodCategory.getId() != foodCategoryId &&
                foodCategory.getName().equals(foodCategoryName)) {
            req.setAttribute(IS_ERROR_EDIT_FOOD_CATEGORY, ERROR);
        } else {
            if (FoodCategoryLogic.edit(foodCategoryId, foodCategoryName)) {
                req.setAttribute(IS_SUCCESS_EDIT_FOOD_CATEGORY, SUCCESS);
            } else {
                req.setAttribute(IS_ERROR_EDIT_FOOD_CATEGORY, ERROR);
            }
        }
        return page;
    }
}
