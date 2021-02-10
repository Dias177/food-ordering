package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.OrderStatusLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class AddOrderStatusCommand implements ActionCommand {

    private static final String PATH_PAGE_ADD_ORDER_STATUS = "path.page.add.order.status";
    private static final String PARAM_NAME_ORDER_STATUS_NAME = "orderStatusName";
    private static final String IS_ERROR_INVALID_ORDER_STATUS_NAME = "isErrorInvalidOrderStatusName";
    private static final String IS_SUCCESS_ADD_ORDER_STATUS_NAME = "isSuccessAddOrderStatusName";
    private static final boolean SUCCESS = true;
    private static final boolean ERROR = true;

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_ADD_ORDER_STATUS);
        String orderStatusName = req.getParameter(PARAM_NAME_ORDER_STATUS_NAME);
        if (OrderStatusLogic.statusExists(orderStatusName)) {
            req.setAttribute(IS_ERROR_INVALID_ORDER_STATUS_NAME, ERROR);
        } else {
            OrderStatusLogic.add(orderStatusName);
            req.setAttribute(IS_SUCCESS_ADD_ORDER_STATUS_NAME, SUCCESS);
        }
        return page;
    }
}
