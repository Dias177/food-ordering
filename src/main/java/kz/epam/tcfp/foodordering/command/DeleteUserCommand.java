package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.ProfileLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class DeleteUserCommand implements ActionCommand {

    private static final String PATH_PAGE_DELETE_USER = "path.page.delete.user";
    private static final String PARAM_NAME_USER_ID = "user_id";
    private static final String IS_SUCCESS_DELETE_USER = "isSuccessDeleteUser";
    private static final String IS_ERROR_DELETE_USER = "isErrorDeleteUser";
    private static final boolean SUCCESS = true;
    private static final boolean ERROR = true;

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
