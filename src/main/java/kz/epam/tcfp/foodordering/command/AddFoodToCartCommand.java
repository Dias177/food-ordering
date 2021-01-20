package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Food;
import kz.epam.tcfp.foodordering.logic.FoodLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Set;

public class AddFoodToCartCommand implements ActionCommand {

    private static final String PARAM_NAME_FOOD_ID = "food_id";
    private static final String CART = "cart";
    private static final String PATH_PAGE_MENU = "path.page.menu";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_MENU);
        String id = req.getParameter(PARAM_NAME_FOOD_ID);
        long idLong = Long.parseLong(id);
        Food food = FoodLogic.getFood(idLong);
        Object cartObject = req.getSession().getAttribute(CART);
        Set<Food> cart = (Set<Food>) cartObject;
        cart.add(food);
        req.getSession().setAttribute(CART, cart);
        return page;
    }
}
