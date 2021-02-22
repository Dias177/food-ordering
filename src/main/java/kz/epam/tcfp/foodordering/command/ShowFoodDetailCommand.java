package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Food;
import kz.epam.tcfp.foodordering.entity.FoodCategory;
import kz.epam.tcfp.foodordering.logic.FoodCategoryLogic;
import kz.epam.tcfp.foodordering.logic.FoodLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_FOOD_DETAIL;

public class ShowFoodDetailCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_FOOD_DETAIL);
        String id = req.getParameter(PARAM_NAME_FOOD_ID_UNDERSCORE);
        long idLong = Long.parseLong(id);
        Food food = FoodLogic.getFood(idLong);
        List<FoodCategory> foodCategoryList = FoodCategoryLogic.getAll();
        req.setAttribute(FOOD, food);
        req.setAttribute(FOOD_CATEGORIES, foodCategoryList);
        return page;
    }
}
