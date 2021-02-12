package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.OrderLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class DeleteOrderCommand implements ActionCommand {

    private static final String PATH_PAGE_ALL_ORDERS = "path.page.all.orders";
    private static final String PARAM_NAME_ORDER_ID = "order_id";
    private static final String IS_SUCCESS_DELETE_ORDER = "isSuccessDeleteOrder";
    private static final String IS_ERROR_DELETE_ORDER = "isErrorDeleteOrder";
    private static final boolean SUCCESS = true;
    private static final boolean ERROR = true;

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_ALL_ORDERS);
        long orderId = Long.parseLong(req.getParameter(PARAM_NAME_ORDER_ID));
        if (OrderLogic.remove(orderId)) {
            req.setAttribute(IS_SUCCESS_DELETE_ORDER, SUCCESS);
        } else {
            req.setAttribute(IS_ERROR_DELETE_ORDER, ERROR);
        }
        return page;
    }
}
