package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.FoodLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class DeleteFoodCommand implements ActionCommand {

    private static final String PARAM_NAME_FOOD_ID = "food_id";
    private static final String IS_SUCCESS_DELETE_FOOD = "isSuccessDeleteFood";
    private static final boolean SUCCESS = true;
    private static final String PATH_PAGE_FOOD_DELETE_INFO = "path.page.food.delete.info";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_FOOD_DELETE_INFO);
        long foodId = Long.parseLong(req.getParameter(PARAM_NAME_FOOD_ID));
        if (FoodLogic.remove(foodId)) {
            req.setAttribute(IS_SUCCESS_DELETE_FOOD, SUCCESS);
        }
        return page;
    }
}
