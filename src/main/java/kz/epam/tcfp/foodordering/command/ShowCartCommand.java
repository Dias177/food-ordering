package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Food;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Set;

public class ShowCartCommand implements ActionCommand {

    private static final String PATH_PAGE_CART = "path.page.cart";
    private static final String IS_CART_EMPTY = "isCartEmpty";
    private static final String CART = "cart";
    private static final boolean TRUE = true;

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_CART);
        Set<Food> cart = (Set<Food>) req.getSession().getAttribute(CART);
        if (cart == null || cart.isEmpty()) {
            req.setAttribute(IS_CART_EMPTY, TRUE);
        }
        return page;
    }
}
