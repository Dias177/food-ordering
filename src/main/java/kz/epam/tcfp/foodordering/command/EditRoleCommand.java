package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Role;
import kz.epam.tcfp.foodordering.logic.RoleLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_EDIT_ROLE;
import static kz.epam.tcfp.foodordering.util.ValueConstants.ERROR;
import static kz.epam.tcfp.foodordering.util.ValueConstants.SUCCESS;

public class EditRoleCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT_ROLE);
        long roleId = Long.parseLong(req.getParameter(PARAM_NAME_ROLE_ID));
        String roleName = req.getParameter(PARAM_NAME_ROLE_NAME);
        Role role = RoleLogic.getRoleByName(roleName);
        if (role.getName() != null && role.getId() != roleId &&
                role.getName().equalsIgnoreCase(roleName)) {
            req.setAttribute(IS_ERROR_EDIT_ROLE, ERROR);
        } else {
            if (RoleLogic.edit(roleId, roleName)) {
                req.setAttribute(IS_SUCCESS_EDIT_ROLE, SUCCESS);
            } else {
                req.setAttribute(IS_ERROR_EDIT_ROLE, ERROR);
            }
        }
        return page;
    }
}
