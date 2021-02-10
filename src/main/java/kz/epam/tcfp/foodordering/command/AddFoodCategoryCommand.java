package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.FoodCategoryLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class AddFoodCategoryCommand implements ActionCommand {

    private static final String PARAM_NAME_FOOD_CATEGORY_NAME = "foodCategoryName";
    private static final String IS_SUCCESS_ADD_FOOD_CATEGORY_NAME = "isSuccessAddFoodCategoryName";
    private static final String IS_ERROR_INVALID_FOOD_CATEGORY_NAME = "isErrorInvalidFoodCategoryName";
    private static final String PATH_PAGE_ADD_FOOD_CATEGORY = "path.page.add.food.category";
    private static final boolean SUCCESS = true;
    private static final boolean ERROR = true;

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page;
        String foodCategoryName = req.getParameter(PARAM_NAME_FOOD_CATEGORY_NAME);
        if (FoodCategoryLogic.categoryExists(foodCategoryName)) {
            req.setAttribute(IS_ERROR_INVALID_FOOD_CATEGORY_NAME, ERROR);
        } else {
            FoodCategoryLogic.add(foodCategoryName);
            req.setAttribute(IS_SUCCESS_ADD_FOOD_CATEGORY_NAME, SUCCESS);
        }
        page = ConfigurationManager.getProperty(PATH_PAGE_ADD_FOOD_CATEGORY);
        return page;
    }
}
