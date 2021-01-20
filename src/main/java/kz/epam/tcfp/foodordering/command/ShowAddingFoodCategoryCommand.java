package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class ShowAddingFoodCategoryCommand implements ActionCommand {

    private static final String PATH_PAGE_ADD_FOOD_CATEGORY = "path.page.add.food.category";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_ADD_FOOD_CATEGORY);
        return page;
    }
}
