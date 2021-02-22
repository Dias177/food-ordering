package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.FoodLogic;
import kz.epam.tcfp.foodordering.logic.OrderLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_FOOD_DELETE_INFO;
import static kz.epam.tcfp.foodordering.util.ValueConstants.SUCCESS;

public class DeleteFoodCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_FOOD_DELETE_INFO);
        long foodId = Long.parseLong(req.getParameter(PARAM_NAME_FOOD_ID_UNDERSCORE));
        if (FoodLogic.remove(foodId)) {
            req.setAttribute(IS_SUCCESS_DELETE_FOOD, SUCCESS);
            OrderLogic.updateAllOrdersPrice();
        }
        return page;
    }
}
