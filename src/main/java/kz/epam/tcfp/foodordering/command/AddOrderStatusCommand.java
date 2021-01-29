package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.OrderStatusLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;
import kz.epam.tcfp.foodordering.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class AddOrderStatusCommand implements ActionCommand {

    private static final String PATH_PAGE_ADD_ORDER_STATUS = "path.page.add.order.status";
    private static final String PARAM_NAME_ORDER_STATUS_NAME = "orderStatusName";
    private static final String MESSAGE_ORDER_STATUS_NAME_ERROR = "message.order.status.name.error";
    private static final String MESSAGE_ADD_ORDER_STATUS_SUCCESS = "message.add.order.status.success";
    private static final String ERROR_INVALID_ORDER_STATUS_NAME = "errorInvalidOrderStatusName";
    private static final String SUCCESS_ADD_ORDER_STATUS_NAME = "successAddOrderStatusName";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_ADD_ORDER_STATUS);
        String orderStatusName = req.getParameter(PARAM_NAME_ORDER_STATUS_NAME);
        if (OrderStatusLogic.statusExists(orderStatusName)) {
            req.setAttribute(ERROR_INVALID_ORDER_STATUS_NAME, MessageManager.getProperty(MESSAGE_ORDER_STATUS_NAME_ERROR));
        } else {
            OrderStatusLogic.add(orderStatusName);
            req.setAttribute(SUCCESS_ADD_ORDER_STATUS_NAME, MessageManager.getProperty(MESSAGE_ADD_ORDER_STATUS_SUCCESS));
        }
        return page;
    }
}
