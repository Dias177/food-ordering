package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.FoodLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;
import kz.epam.tcfp.foodordering.util.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class AddFoodCommand implements ActionCommand {

    private static final String PARAM_NAME_FOOD_NAME = "foodName";
    private static final String PARAM_NAME_FOOD_CATEGORY = "foodCategory";
    private static final String PARAM_NAME_FOOD_DESCRIPTION = "foodDescription";
    private static final String PARAM_NAME_FOOD_PRICE = "foodPrice";
    private static final String PATH_PAGE_ADD_FOOD = "path.page.add.food";
    private static final String ERROR_INVALID_FOOD_NAME = "errorInvalidFoodName";
    private static final String ERROR_INVALID_FOOD_PRICE = "errorInvalidFoodPrice";
    private static final String SUCCESS_ADD_FOOD = "successAddFood";
    private static final String MESSAGE_FOOD_NAME_ERROR = "message.food.name.error";
    private static final String MESSAGE_FOOD_PRICE_ERROR = "message.food.price.error";
    private static final String MESSAGE_ADD_FOOD_SUCCESS = "message.add.food.success";

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
            req.setAttribute(ERROR_INVALID_FOOD_NAME, MessageManager.getProperty(MESSAGE_FOOD_NAME_ERROR));
            isValidData = false;
        }
        if (Double.parseDouble(foodPrice) < 0) {
            req.setAttribute(ERROR_INVALID_FOOD_PRICE, MessageManager.getProperty(MESSAGE_FOOD_PRICE_ERROR));
            isValidData = false;
        }
        if (isValidData) {
            FoodLogic.add(foodCategoryId, foodName, foodDescription, foodPriceDouble);
            req.setAttribute(SUCCESS_ADD_FOOD, MessageManager.getProperty(MESSAGE_ADD_FOOD_SUCCESS));
        }
        page = ConfigurationManager.getProperty(PATH_PAGE_ADD_FOOD);
        return page;
    }
}
