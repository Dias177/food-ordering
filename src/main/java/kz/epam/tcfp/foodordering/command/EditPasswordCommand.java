package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.ProfileLogic;
import kz.epam.tcfp.foodordering.logic.RegisterLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class EditPasswordCommand implements ActionCommand {

    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_CONFIRMED_PASSWORD = "confirmedPassword";
    private static final String IS_ERROR_INVALID_PASSWORD = "isErrorInvalidPassword";
    private static final String IS_ERROR_WRONG_CONFIRMED_PASSWORD = "isErrorWrongConfirmedPassword";
    private static final String PATH_PAGE_EDIT_PASSWORD = "path.page.edit.password";
    private static final String USER_ID = "userId";
    private static final String IS_SUCCESS_EDIT_PASSWORD = "isSuccessEditPassword";
    private static final boolean SUCCESS = true;
    private static final boolean ERROR = true;

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT_PASSWORD);
        boolean isDataValid = true;
        long userId = (long) req.getSession().getAttribute(USER_ID);
        String password = req.getParameter(PARAM_NAME_PASSWORD);
        String confirmedPassword = req.getParameter(PARAM_NAME_CONFIRMED_PASSWORD);
        if (!RegisterLogic.isValidPassword(password)) {
            req.setAttribute(IS_ERROR_INVALID_PASSWORD, ERROR);
            isDataValid = false;
        }
        if (!RegisterLogic.isCorrectConfirmedPassword(confirmedPassword, password)) {
            req.setAttribute(IS_ERROR_WRONG_CONFIRMED_PASSWORD, ERROR);
            isDataValid = false;
        }
        if (isDataValid && ProfileLogic.editPassword(userId, password)) {
            req.setAttribute(IS_SUCCESS_EDIT_PASSWORD, SUCCESS);
        }
        return page;
    }
}
