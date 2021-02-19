package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.RoleLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class AddRoleCommand implements ActionCommand {

    private static final String PATH_PAGE_ADD_ROLE = "path.page.add.role";
    private static final String PARAM_NAME_ROLE_NAME = "roleName";
    private static final String IS_ERROR_ADD_ROLE = "isErrorAddRole";
    private static final String IS_SUCCESS_ADD_ROLE = "isSuccessAddRole";
    private static final boolean SUCCESS = true;
    private static final boolean ERROR = true;

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
