package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class ShowAddingOrderStatusCommand implements ActionCommand {

    private static final String PATH_PAGE_ADD_ORDER_STATUS = "path.page.add.order.status";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_ADD_ORDER_STATUS);
        return page;
    }
}
