package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.OrderLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_ALL_ORDERS;
import static kz.epam.tcfp.foodordering.util.ValueConstants.ERROR;
import static kz.epam.tcfp.foodordering.util.ValueConstants.SUCCESS;

public class EditOrderCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_ALL_ORDERS);
        long orderId = Long.parseLong(req.getParameter(PARAM_NAME_ORDER_ID_CAMELCASE));
        long orderStatusId = Long.parseLong(req.getParameter(PARAM_NAME_ORDER_STATUS_ID + orderId));
        if (OrderLogic.updateStatus(orderId, orderStatusId)) {
             req.setAttribute(IS_SUCCESS_EDITING_ORDER_STATUS, SUCCESS);
        } else {
            req.setAttribute(IS_ERROR_EDITING_ORDER_STATUS, ERROR);
        }
        return page;
    }
}
