package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Food;
import kz.epam.tcfp.foodordering.logic.FoodLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class EditFoodCommand implements ActionCommand {

    private static final String PATH_PAGE_EDIT_FOOD = "path.page.edit.food";
    private static final String PARAM_NAME_FOOD_ID = "foodId";
    private static final String PARAM_NAME_FOOD_NAME = "foodName";
    private static final String PARAM_NAME_FOOD_CATEGORY_ID = "foodCategoryId";
    private static final String PARAM_NAME_FOOD_DESCRIPTION = "foodDescription";
    private static final String PARAM_NAME_FOOD_PRICE = "foodPrice";
    private static final String IS_SUCCESS_EDIT_FOOD = "isSuccessEditFood";
    private static final String IS_ERROR_INVALID_FOOD_PRICE = "isErrorInvalidFoodPrice";
    private static final String IS_ERROR_INVALID_FOOD_NAME = "isErrorInvalidFoodName";
    private static final double MIN_FOOD_PRICE = 0;
    private static final boolean SUCCESS = true;
    private static final boolean ERROR = true;

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT_FOOD);
        long foodId = Long.parseLong(req.getParameter(PARAM_NAME_FOOD_ID));
        String foodName = req.getParameter(PARAM_NAME_FOOD_NAME);
        long foodCategoryId = Long.parseLong(req.getParameter(PARAM_NAME_FOOD_CATEGORY_ID));
        String foodDescription = req.getParameter(PARAM_NAME_FOOD_DESCRIPTION);
        double foodPrice = Double.parseDouble(req.getParameter(PARAM_NAME_FOOD_PRICE));
        boolean isValidData = true;
        Food food = FoodLogic.getFood(foodName);
        if (food.getId() != foodId && food.getName().equals(foodName)) {
            req.setAttribute(IS_ERROR_INVALID_FOOD_NAME, ERROR);
            isValidData = false;
        }
        if (foodPrice < MIN_FOOD_PRICE) {
            req.setAttribute(IS_ERROR_INVALID_FOOD_PRICE, ERROR);
            isValidData = false;
        }
        if (isValidData && FoodLogic.edit(foodId, foodName, foodCategoryId, foodDescription, foodPrice)) {
            req.setAttribute(IS_SUCCESS_EDIT_FOOD, SUCCESS);
        }
        return page;
    }
}
