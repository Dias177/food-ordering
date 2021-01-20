package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Food;
import kz.epam.tcfp.foodordering.logic.FoodLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class ShowFoodDetailCommand implements ActionCommand {

    private static final String PATH_PAGE_FOOD_DETAIL = "path.page.food.detail";
    private static final String ATTR_NAME_FOOD = "food";
    private static final String PARAM_NAME_FOOD_ID = "food_id";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_FOOD_DETAIL);
        String id = req.getParameter(PARAM_NAME_FOOD_ID);
        long idLong = Long.parseLong(id);
        Food food = FoodLogic.getFood(idLong);
        req.setAttribute(ATTR_NAME_FOOD, food);
        return page;
    }
}
