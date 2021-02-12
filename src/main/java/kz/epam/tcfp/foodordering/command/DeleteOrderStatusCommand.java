package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.OrderStatusLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class DeleteOrderStatusCommand implements ActionCommand {

    private static final String PATH_PAGE_DELETE_ORDER_STATUS = "path.page.delete.order.status";
    private static final String PARAM_NAME_ORDER_STATUS = "orderStatus";
    private static final String IS_SUCCESS_DELETE_ORDER_STATUS = "isSuccessDeleteOrderStatus";
    private static final String IS_ERROR_DELETE_ORDER_STATUS = "isErrorDeleteOrderStatus";
    private static final boolean SUCCESS = true;
    private static final boolean ERROR = true;

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_DELETE_ORDER_STATUS);
        long orderStatusId = Long.parseLong(req.getParameter(PARAM_NAME_ORDER_STATUS));
        if (OrderStatusLogic.remove(orderStatusId)) {
            req.setAttribute(IS_SUCCESS_DELETE_ORDER_STATUS, SUCCESS);
        } else {
            req.setAttribute(IS_ERROR_DELETE_ORDER_STATUS, ERROR);
        }
        return page;
    }
}
