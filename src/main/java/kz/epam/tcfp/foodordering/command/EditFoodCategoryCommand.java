package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.FoodCategory;
import kz.epam.tcfp.foodordering.logic.FoodCategoryLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_EDIT_FOOD_CATEGORY;
import static kz.epam.tcfp.foodordering.util.ValueConstants.ERROR;
import static kz.epam.tcfp.foodordering.util.ValueConstants.SUCCESS;

public class EditFoodCategoryCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT_FOOD_CATEGORY);
        long foodCategoryId = Long.parseLong(req.getParameter(PARAM_NAME_FOOD_CATEGORY_ID_CAMELCASE));
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
