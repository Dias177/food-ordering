package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.FoodCategory;
import kz.epam.tcfp.foodordering.logic.FoodCategoryLogic;
import kz.epam.tcfp.foodordering.logic.FoodLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddFoodCommand implements ActionCommand {

    private static final String PARAM_NAME_FOOD_NAME = "foodName";
    private static final String PARAM_NAME_FOOD_CATEGORY = "foodCategory";
    private static final String PARAM_NAME_FOOD_DESCRIPTION = "foodDescription";
    private static final String PARAM_NAME_FOOD_PRICE = "foodPrice";
    private static final String PATH_PAGE_ADD_FOOD = "path.page.add.food";
    private static final String IS_ERROR_INVALID_FOOD_NAME = "isErrorInvalidFoodName";
    private static final String IS_ERROR_INVALID_FOOD_PRICE = "isErrorInvalidFoodPrice";
    private static final String IS_SUCCESS_ADD_FOOD = "isSuccessAddFood";
    private static final boolean ERROR = true;
    private static final boolean SUCCESS = true;
    private static final String FOOD_CATEGORIES = "foodCategories";

    @Override
    public String execute(HttpServletRequest req) throws DaoException {
        String page;
        String foodName = req.getParameter(PARAM_NAME_FOOD_NAME);
        String foodCategory = req.getParameter(PARAM_NAME_FOOD_CATEGORY);
        String foodDescription = req.getParameter(PARAM_NAME_FOOD_DESCRIPTION);
        String foodPrice = req.getParameter(PARAM_NAME_FOOD_PRICE);
        boolean isValidData = true;
        long foodCategoryId = Long.parseLong(foodCategory);
        double foodPriceDouble = Double.parseDouble(foodPrice);
        if (FoodLogic.nameExists(foodName)) {
            req.setAttribute(IS_ERROR_INVALID_FOOD_NAME, ERROR);
            isValidData = false;
        }
        if (Double.parseDouble(foodPrice) < 0) {
            req.setAttribute(IS_ERROR_INVALID_FOOD_PRICE, ERROR);
            isValidData = false;
        }
        if (isValidData) {
            FoodLogic.add(foodCategoryId, foodName, foodDescription, foodPriceDouble);
            req.setAttribute(IS_SUCCESS_ADD_FOOD, SUCCESS);
        }
        List<FoodCategory> foodCategoryList = FoodCategoryLogic.getAll();
        req.setAttribute(FOOD_CATEGORIES, foodCategoryList);
        page = ConfigurationManager.getProperty(PATH_PAGE_ADD_FOOD);
        return page;
    }
}
