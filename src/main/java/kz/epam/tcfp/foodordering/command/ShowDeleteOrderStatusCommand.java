package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.OrderStatus;
import kz.epam.tcfp.foodordering.logic.OrderStatusLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public class ShowDeleteOrderStatusCommand implements ActionCommand {

    private static final String PATH_PAGE_DELETE_ORDER_STATUS = "path.page.delete.order.status";
    private static final String ORDER_STATUSES = "orderStatuses";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_DELETE_ORDER_STATUS);
        List<OrderStatus> orderStatusList = OrderStatusLogic.getAll();
        req.setAttribute(ORDER_STATUSES, orderStatusList);
        return page;
    }
}
