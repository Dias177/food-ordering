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

public class DeleteOrderCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_ALL_ORDERS);
        long orderId = Long.parseLong(req.getParameter(PARAM_NAME_ORDER_ID_UNDERSCORE));
        if (OrderLogic.remove(orderId)) {
            req.setAttribute(IS_SUCCESS_DELETE_ORDER, SUCCESS);
        } else {
            req.setAttribute(IS_ERROR_DELETE_ORDER, ERROR);
        }
        return page;
    }
}
