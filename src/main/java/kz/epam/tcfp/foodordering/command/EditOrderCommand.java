package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.OrderLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class EditOrderCommand implements ActionCommand {

    private static final String PATH_PAGE_ALL_ORDERS = "path.page.all.orders";
    private static final String PARAM_NAME_ORDER_ID = "orderId";
    private static final String PARAM_NAME_ORDER_STATUS_ID = "orderStatusId";
    private static final String IS_SUCCESS_EDITING_ORDER_STATUS = "isSuccessEditingOrderStatus";
    private static final String IS_ERROR_EDITING_ORDER_STATUS = "isErrorEditingOrderStatus";
    private static final boolean SUCCESS = true;
    private static final boolean ERROR = true;

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_ALL_ORDERS);
        long orderId = Long.parseLong(req.getParameter(PARAM_NAME_ORDER_ID));
        long orderStatusId = Long.parseLong(req.getParameter(PARAM_NAME_ORDER_STATUS_ID + orderId));
        if (OrderLogic.updateStatus(orderId, orderStatusId)) {
             req.setAttribute(IS_SUCCESS_EDITING_ORDER_STATUS, SUCCESS);
        } else {
            req.setAttribute(IS_ERROR_EDITING_ORDER_STATUS, ERROR);
        }
        return page;
    }
}
