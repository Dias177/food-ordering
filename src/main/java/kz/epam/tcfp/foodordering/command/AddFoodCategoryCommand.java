package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.FoodCategoryLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;
import kz.epam.tcfp.foodordering.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class AddFoodCategoryCommand implements ActionCommand {

    private static final String PARAM_NAME_FOOD_CATEGORY_NAME = "foodCategoryName";
    private static final String SUCCESS_ADD_FOOD_CATEGORY_NAME = "successAddFoodCategoryName";
    private static final String ERROR_INVALID_FOOD_CATEGORY_NAME = "errorInvalidFoodCategoryName";
    private static final String MESSAGE_FOOD_CATEGORY_NAME_ERROR = "message.food.category.name.error";
    private static final String MESSAGE_ADD_FOOD_CATEGORY_SUCCESS = "message.add.food.category.success";
    private static final String PATH_PAGE_ADD_FOOD_CATEGORY = "path.page.add.food.category";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page;
        String foodCategoryName = req.getParameter(PARAM_NAME_FOOD_CATEGORY_NAME);
        if (FoodCategoryLogic.categoryExists(foodCategoryName)) {
            req.setAttribute(ERROR_INVALID_FOOD_CATEGORY_NAME, MessageManager.getProperty(MESSAGE_FOOD_CATEGORY_NAME_ERROR));
        } else {
            FoodCategoryLogic.add(foodCategoryName);
            req.setAttribute(SUCCESS_ADD_FOOD_CATEGORY_NAME, MessageManager.getProperty(MESSAGE_ADD_FOOD_CATEGORY_SUCCESS));
        }
        page = ConfigurationManager.getProperty(PATH_PAGE_ADD_FOOD_CATEGORY);
        return page;
    }
}
