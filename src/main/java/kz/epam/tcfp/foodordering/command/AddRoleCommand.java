package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.RoleLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_ADD_ROLE;
import static kz.epam.tcfp.foodordering.util.ValueConstants.ERROR;
import static kz.epam.tcfp.foodordering.util.ValueConstants.SUCCESS;

public class AddRoleCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_ADD_ROLE);
        String roleName = req.getParameter(PARAM_NAME_ROLE_NAME);
        if (RoleLogic.add(roleName)) {
            req.setAttribute(IS_SUCCESS_ADD_ROLE, SUCCESS);
        } else {
            req.setAttribute(IS_ERROR_ADD_ROLE, ERROR);
        }
        return page;
    }
}
