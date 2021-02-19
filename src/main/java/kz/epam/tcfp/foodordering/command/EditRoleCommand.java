package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Role;
import kz.epam.tcfp.foodordering.logic.OrderStatusLogic;
import kz.epam.tcfp.foodordering.logic.RoleLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class EditRoleCommand implements ActionCommand {

    private static final String PATH_PAGE_EDIT_ROLE = "path.page.edit.role";
    private static final String PARAM_NAME_ROLE_ID = "roleId";
    private static final String PARAM_NAME_ROLE_NAME = "roleName";
    private static final String IS_SUCCESS_EDIT_ROLE = "isSuccessEditRole";
    private static final String IS_ERROR_EDIT_ROLE = "isErrorEditRole";
    private static final boolean SUCCESS = true;
    private static final boolean ERROR = true;

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
