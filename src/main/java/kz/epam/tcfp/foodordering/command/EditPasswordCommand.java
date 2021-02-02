package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.ProfileLogic;
import kz.epam.tcfp.foodordering.logic.RegisterLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;
import kz.epam.tcfp.foodordering.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class EditPasswordCommand implements ActionCommand {

    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_CONFIRMED_PASSWORD = "confirmedPassword";
    private static final String ERROR_INVALID_PASSWORD = "errorInvalidPassword";
    private static final String MESSAGE_PASSWORD_ERROR = "message.password.error";
    private static final String ERROR_WRONG_CONFIRMED_PASSWORD = "errorWrongConfirmedPassword";
    private static final String MESSAGE_CONFIRMED_PASSWORD_ERROR = "message.confirmed.password.error";
    private static final String PATH_PAGE_EDIT_PASSWORD = "path.page.edit.password";
    private static final String USER_ID = "userId";
    private static final String SUCCESS_EDIT_PASSWORD = "successEditPassword";
    private static final String MESSAGE_EDIT_PASSWORD_SUCCESS = "message.edit.password.success";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT_PASSWORD);
        boolean isDataValid = true;
        long userId = (long) req.getSession().getAttribute(USER_ID);
        String password = req.getParameter(PARAM_NAME_PASSWORD);
        String confirmedPassword = req.getParameter(PARAM_NAME_CONFIRMED_PASSWORD);
        if (!RegisterLogic.isValidPassword(password)) {
            req.setAttribute(ERROR_INVALID_PASSWORD, MessageManager.getProperty(MESSAGE_PASSWORD_ERROR));
            isDataValid = false;
        }
        if (!RegisterLogic.isCorrectConfirmedPassword(confirmedPassword, password)) {
            req.setAttribute(ERROR_WRONG_CONFIRMED_PASSWORD, MessageManager.getProperty(MESSAGE_CONFIRMED_PASSWORD_ERROR));
            isDataValid = false;
        }
        if (isDataValid && ProfileLogic.editPassword(userId, password)) {
            req.setAttribute(SUCCESS_EDIT_PASSWORD, MessageManager.getProperty(MESSAGE_EDIT_PASSWORD_SUCCESS));
        }
        return page;
    }
}
