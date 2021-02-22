package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.RoleLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_DELETE_ROLE;
import static kz.epam.tcfp.foodordering.util.ValueConstants.ERROR;
import static kz.epam.tcfp.foodordering.util.ValueConstants.SUCCESS;

public class DeleteRoleCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_DELETE_ROLE);
        long roleId = Long.parseLong(req.getParameter(PARAM_NAME_ROLE));
        if (RoleLogic.remove(roleId)) {
            req.setAttribute(IS_SUCCESS_DELETE_ROLE, SUCCESS);
        } else {
            req.setAttribute(IS_ERROR_DELETE_ROLE, ERROR);
        }
        return page;
    }
}
