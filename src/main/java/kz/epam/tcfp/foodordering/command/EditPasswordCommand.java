package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.ProfileLogic;
import kz.epam.tcfp.foodordering.logic.RegisterLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_EDIT_PASSWORD;
import static kz.epam.tcfp.foodordering.util.ValueConstants.ERROR;
import static kz.epam.tcfp.foodordering.util.ValueConstants.SUCCESS;

public class EditPasswordCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT_PASSWORD);
        boolean isDataValid = true;
        long userId = (long) req.getSession().getAttribute(USER_ID);
        String password = req.getParameter(PARAM_NAME_PASSWORD);
        String confirmedPassword = req.getParameter(PARAM_NAME_CONFIRMED_PASSWORD);
        if (!RegisterLogic.isPasswordValid(password)) {
            req.setAttribute(IS_ERROR_INVALID_PASSWORD, ERROR);
            isDataValid = false;
        }
        if (!RegisterLogic.isConfirmedPasswordCorrect(confirmedPassword, password)) {
            req.setAttribute(IS_ERROR_WRONG_CONFIRMED_PASSWORD, ERROR);
            isDataValid = false;
        }
        if (isDataValid && ProfileLogic.editPassword(userId, password)) {
            req.setAttribute(IS_SUCCESS_EDIT_PASSWORD, SUCCESS);
        }
        return page;
    }
}
