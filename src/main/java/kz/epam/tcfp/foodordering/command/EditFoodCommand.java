package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Food;
import kz.epam.tcfp.foodordering.logic.FoodLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_EDIT_FOOD;
import static kz.epam.tcfp.foodordering.util.ValueConstants.*;


public class EditFoodCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT_FOOD);
        long foodId = Long.parseLong(req.getParameter(PARAM_NAME_FOOD_ID_CAMELCASE));
        String foodName = req.getParameter(PARAM_NAME_FOOD_NAME);
        long foodCategoryId = Long.parseLong(req.getParameter(PARAM_NAME_FOOD_CATEGORY_ID_CAMELCASE));
        String foodDescription = req.getParameter(PARAM_NAME_FOOD_DESCRIPTION);
        double foodPrice = Double.parseDouble(req.getParameter(PARAM_NAME_FOOD_PRICE));
        boolean isDataValid = true;
        Food food = FoodLogic.getFood(foodName);
        if (food.getId() != foodId && food.getName() != null) {
            req.setAttribute(IS_ERROR_INVALID_FOOD_NAME, ERROR);
            isDataValid = false;
        }
        if (foodPrice < MIN_FOOD_PRICE) {
            req.setAttribute(IS_ERROR_INVALID_FOOD_PRICE, ERROR);
            isDataValid = false;
        }
        if (isDataValid && FoodLogic.edit(foodId, foodName, foodCategoryId, foodDescription, foodPrice)) {
            req.setAttribute(IS_SUCCESS_EDIT_FOOD, SUCCESS);
        }
        return page;
    }
}
