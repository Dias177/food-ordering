package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.RoleLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class DeleteRoleCommand implements ActionCommand {

    private static final String PATH_PAGE_DELETE_ROLE = "path.page.delete.role";
    private static final String PARAM_NAME_ROLE = "role";
    private static final String IS_SUCCESS_DELETE_ROLE = "isSuccessDeleteRole";
    private static final String IS_ERROR_DELETE_ROLE = "isErrorDeleteRole";
    private static final boolean SUCCESS = true;
    private static final boolean ERROR = true;

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
