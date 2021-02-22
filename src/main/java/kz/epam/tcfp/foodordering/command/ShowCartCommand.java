package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Food;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Set;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.CART;
import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.IS_CART_EMPTY;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_CART;
import static kz.epam.tcfp.foodordering.util.ValueConstants.TRUE;

public class ShowCartCommand implements ActionCommand {

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
