package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.OrderStatusLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_ADD_ORDER_STATUS;
import static kz.epam.tcfp.foodordering.util.ValueConstants.ERROR;
import static kz.epam.tcfp.foodordering.util.ValueConstants.SUCCESS;

public class AddOrderStatusCommand implements ActionCommand {

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
