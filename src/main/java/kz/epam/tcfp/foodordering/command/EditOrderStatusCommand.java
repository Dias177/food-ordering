package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.OrderLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;
import kz.epam.tcfp.foodordering.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class EditOrderStatusCommand implements ActionCommand {

    private static final String PATH_PAGE_ALL_ORDERS = "path.page.all.orders";
    private static final String PARAM_NAME_ORDER_ID = "orderId";
    private static final String PARAM_NAME_ORDER_STATUS_ID = "orderStatusId";
    private static final String SUCCESS_EDITING_ORDER_STATUS = "successEditingOrderStatus";
    private static final String MESSAGE_EDIT_ORDER_STATUS_SUCCESS = "message.edit.order.status.success";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_ALL_ORDERS);
        long orderId = Long.parseLong(req.getParameter(PARAM_NAME_ORDER_ID));
        long orderStatusId = Long.parseLong(req.getParameter(PARAM_NAME_ORDER_STATUS_ID + orderId));
         if (OrderLogic.updateStatus(orderId, orderStatusId)) {
             req.setAttribute(SUCCESS_EDITING_ORDER_STATUS, MessageManager.getProperty(MESSAGE_EDIT_ORDER_STATUS_SUCCESS));
         }
        return page;
    }
}
