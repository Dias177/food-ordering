package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Food;
import kz.epam.tcfp.foodordering.logic.FoodLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;
import kz.epam.tcfp.foodordering.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class EditFoodCommand implements ActionCommand {

    private static final String PATH_PAGE_EDIT_FOOD = "path.page.edit.food";
    private static final String PARAM_NAME_FOOD_ID = "foodId";
    private static final String PARAM_NAME_FOOD_NAME = "foodName";
    private static final String PARAM_NAME_FOOD_CATEGORY_ID = "foodCategoryId";
    private static final String PARAM_NAME_FOOD_DESCRIPTION = "foodDescription";
    private static final String PARAM_NAME_FOOD_PRICE = "foodPrice";
    private static final String SUCCESS_EDIT_FOOD = "successEditFood";
    private static final String ERROR_INVALID_FOOD_PRICE = "errorInvalidFoodPrice";
    private static final String ERROR_INVALID_FOOD_NAME = "errorInvalidFoodName";
    private static final String MESSAGE_EDIT_FOOD_SUCCESS = "message.edit.food.success";
    private static final String MESSAGE_FOOD_NAME_ERROR = "message.food.name.error";
    private static final String MESSAGE_FOOD_PRICE_ERROR = "message.food.price.error";


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
            req.setAttribute(ERROR_INVALID_FOOD_NAME, MessageManager.getProperty(MESSAGE_FOOD_NAME_ERROR));
            isValidData = false;
        }
        if (foodPrice < 0) {
            req.setAttribute(ERROR_INVALID_FOOD_PRICE, MessageManager.getProperty(MESSAGE_FOOD_PRICE_ERROR));
            isValidData = false;
        }
        if (isValidData && FoodLogic.edit(foodId, foodName, foodCategoryId, foodDescription, foodPrice)) {
            req.setAttribute(SUCCESS_EDIT_FOOD, MessageManager.getProperty(MESSAGE_EDIT_FOOD_SUCCESS));
        }
        return page;
    }
}
