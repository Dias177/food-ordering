package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.FoodCategory;
import kz.epam.tcfp.foodordering.logic.FoodCategoryLogic;
import kz.epam.tcfp.foodordering.logic.FoodLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_ADD_FOOD;
import static kz.epam.tcfp.foodordering.util.ValueConstants.*;

public class AddFoodCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws DaoException {
        String page;
        String foodName = req.getParameter(PARAM_NAME_FOOD_NAME);
        String foodCategory = req.getParameter(PARAM_NAME_FOOD_CATEGORY);
        String foodDescription = req.getParameter(PARAM_NAME_FOOD_DESCRIPTION);
        String foodPrice = req.getParameter(PARAM_NAME_FOOD_PRICE);
        boolean isDataValid = true;
        long foodCategoryId = Long.parseLong(foodCategory);
        double foodPriceDouble = Double.parseDouble(foodPrice);
        if (FoodLogic.nameExists(foodName)) {
            req.setAttribute(IS_ERROR_INVALID_FOOD_NAME, ERROR);
            isDataValid = false;
        }
        if (foodPriceDouble < MIN_FOOD_PRICE) {
            req.setAttribute(IS_ERROR_INVALID_FOOD_PRICE, ERROR);
            isDataValid = false;
        }
        if (isDataValid) {
            FoodLogic.add(foodCategoryId, foodName, foodDescription, foodPriceDouble);
            req.setAttribute(IS_SUCCESS_ADD_FOOD, SUCCESS);
        }
        List<FoodCategory> foodCategoryList = FoodCategoryLogic.getAll();
        req.setAttribute(FOOD_CATEGORIES, foodCategoryList);
        page = ConfigurationManager.getProperty(PATH_PAGE_ADD_FOOD);
        return page;
    }
}
