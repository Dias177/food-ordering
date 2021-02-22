package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.ProfileLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_DELETE_USER;
import static kz.epam.tcfp.foodordering.util.ValueConstants.ERROR;
import static kz.epam.tcfp.foodordering.util.ValueConstants.SUCCESS;

public class DeleteUserCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_DELETE_USER);
        long userId = Long.parseLong(req.getParameter(PARAM_NAME_USER_ID));
        if (ProfileLogic.remove(userId)) {
            req.setAttribute(IS_SUCCESS_DELETE_USER, SUCCESS);
        } else {
            req.setAttribute(IS_ERROR_DELETE_USER, ERROR);
        }
        return page;
    }
}
