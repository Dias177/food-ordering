package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.OrderStatus;
import kz.epam.tcfp.foodordering.logic.OrderStatusLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public class ShowEditingOrderStatusCommand implements ActionCommand {

    private static final String PATH_PAGE_EDIT_ORDER_STATUS = "path.page.edit.order.status";
    private static final String ORDER_STATUSES = "orderStatuses";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT_ORDER_STATUS);
        List<OrderStatus> orderStatuses = OrderStatusLogic.getAll();
        req.setAttribute(ORDER_STATUSES, orderStatuses);
        return page;
    }
}
