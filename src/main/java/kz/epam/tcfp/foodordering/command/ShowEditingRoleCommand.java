package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Role;
import kz.epam.tcfp.foodordering.logic.RoleLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.ROLES;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_EDIT_ROLE;

public class ShowEditingRoleCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT_ROLE);
        List<Role> roles = RoleLogic.getAll();
        req.setAttribute(ROLES, roles);
        return page;
    }
}
