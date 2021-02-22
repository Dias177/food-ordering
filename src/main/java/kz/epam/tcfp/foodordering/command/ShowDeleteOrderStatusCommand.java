package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.OrderStatus;
import kz.epam.tcfp.foodordering.logic.OrderStatusLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.ORDER_STATUSES;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_DELETE_ORDER_STATUS;

public class ShowDeleteOrderStatusCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_DELETE_ORDER_STATUS);
        List<OrderStatus> orderStatusList = OrderStatusLogic.getAll();
        req.setAttribute(ORDER_STATUSES, orderStatusList);
        return page;
    }
}
