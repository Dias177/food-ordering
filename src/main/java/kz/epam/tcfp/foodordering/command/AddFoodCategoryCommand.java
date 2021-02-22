package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.FoodCategoryLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_ADD_FOOD_CATEGORY;
import static kz.epam.tcfp.foodordering.util.ValueConstants.ERROR;
import static kz.epam.tcfp.foodordering.util.ValueConstants.SUCCESS;

public class AddFoodCategoryCommand implements ActionCommand {

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
