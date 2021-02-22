package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.FoodCategoryLogic;
import kz.epam.tcfp.foodordering.logic.OrderLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_DELETE_FOOD_CATEGORY;
import static kz.epam.tcfp.foodordering.util.ValueConstants.ERROR;
import static kz.epam.tcfp.foodordering.util.ValueConstants.SUCCESS;

public class DeleteFoodCategoryCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_DELETE_FOOD_CATEGORY);
        long foodCategoryId = Long.parseLong(req.getParameter(PARAM_NAME_FOOD_CATEGORY));
        if (FoodCategoryLogic.remove(foodCategoryId)) {
            req.setAttribute(IS_SUCCESS_DELETE_FOOD_CATEGORY, SUCCESS);
            OrderLogic.updateAllOrdersPrice();
        } else {
            req.setAttribute(IS_ERROR_DELETE_FOOD_CATEGORY, ERROR);
        }
        return page;
    }
}
