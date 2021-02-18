package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.OrderStatus;
import kz.epam.tcfp.foodordering.logic.OrderStatusLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class EditOrderStatusCommand implements ActionCommand {

    private static final String PATH_PAGE_EDIT_ORDER_STATUS = "path.page.edit.order.status";
    private static final String PARAM_NAME_ORDER_STATUS_ID = "orderStatusId";
    private static final String PARAM_NAME_ORDER_STATUS_NAME = "orderStatusName";
    private static final String IS_SUCCESS_EDIT_ORDER_STATUS = "isSuccessEditOrderStatus";
    private static final String IS_ERROR_EDIT_ORDER_STATUS = "isErrorEditOrderStatus";
    private static final boolean SUCCESS = true;
    private static final boolean ERROR = true;

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT_ORDER_STATUS);
        long orderStatusId = Long.parseLong(req.getParameter(PARAM_NAME_ORDER_STATUS_ID));
        String orderStatusName = req.getParameter(PARAM_NAME_ORDER_STATUS_NAME);
        OrderStatus orderStatus = OrderStatusLogic.getOrderStatusByName(orderStatusName);
        if (orderStatus.getName() != null && orderStatus.getId() != orderStatusId &&
                orderStatus.getName().equals(orderStatusName)) {
            req.setAttribute(IS_ERROR_EDIT_ORDER_STATUS, ERROR);
        } else {
            if (OrderStatusLogic.edit(orderStatusId, orderStatusName)) {
                req.setAttribute(IS_SUCCESS_EDIT_ORDER_STATUS, SUCCESS);
            } else {
                req.setAttribute(IS_ERROR_EDIT_ORDER_STATUS, ERROR);
            }
        }
        return page;
    }
}
