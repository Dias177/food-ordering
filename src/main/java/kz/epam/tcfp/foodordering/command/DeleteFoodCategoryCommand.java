package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.FoodCategoryLogic;
import kz.epam.tcfp.foodordering.logic.OrderLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class DeleteFoodCategoryCommand implements ActionCommand {

    private static final String PATH_PAGE_DELETE_FOOD_CATEGORY = "path.page.delete.food.category";
    private static final String PARAM_NAME_FOOD_CATEGORY = "foodCategory";
    private static final String IS_SUCCESS_DELETE_FOOD_CATEGORY = "isSuccessDeleteFoodCategory";
    private static final String IS_ERROR_DELETE_FOOD_CATEGORY = "isErrorDeleteFoodCategory";
    private static final boolean SUCCESS = true;
    private static final boolean ERROR = true;

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
