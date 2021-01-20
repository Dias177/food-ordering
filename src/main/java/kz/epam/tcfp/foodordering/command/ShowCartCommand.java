package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;
import kz.epam.tcfp.foodordering.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class ShowCartCommand implements ActionCommand {

    private static final String PATH_PAGE_CART = "path.page.cart";
    private static final String MESSAGE_CART_EMPTY = "message.cart.empty";
    private static final String CART_EMPTY = "cartEmpty";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_CART);
        req.setAttribute(CART_EMPTY, MessageManager.getProperty(MESSAGE_CART_EMPTY));
        return page;
    }
}
