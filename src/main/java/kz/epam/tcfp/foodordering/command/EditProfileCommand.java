package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.ProfileLogic;
import kz.epam.tcfp.foodordering.logic.RegisterLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;

public class EditProfileCommand implements ActionCommand {

    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_FIRST_NAME = "firstName";
    private static final String PARAM_NAME_LAST_NAME = "lastName";
    private static final String PARAM_NAME_PHONE_NUMBER = "phoneNumber";
    private static final String PARAM_NAME_BIRTHDAY = "birthday";
    private static final String USER_ID = "userId";
    private static final String IS_ERROR_INVALID_EMAIL = "isErrorInvalidEmail";
    private static final String IS_ERROR_INVALID_FIRST_NAME = "isErrorInvalidFirstName";
    private static final String IS_ERROR_INVALID_LAST_NAME = "isErrorInvalidLastName";
    private static final String IS_ERROR_INVALID_BIRTHDAY = "isErrorInvalidBirthday";
    private static final String IS_ERROR_INVALID_PHONE_NUMBER = "isErrorInvalidPhoneNumber";
    private static final String PATH_PAGE_EDIT_PROFILE = "path.page.edit.profile";
    private static final String IS_SUCCESS_EDIT_PROFILE = "isSuccessEditProfile";
    private static final boolean SUCCESS = true;
    private static final boolean ERROR = true;

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT_PROFILE);
        boolean isDataValid = true;
        long userId = (long) req.getSession().getAttribute(USER_ID);
        String email = req.getParameter(PARAM_NAME_EMAIL);
        String firstName = req.getParameter(PARAM_NAME_FIRST_NAME);
        String lastName = req.getParameter(PARAM_NAME_LAST_NAME);
        String phoneNumber = req.getParameter(PARAM_NAME_PHONE_NUMBER);
        String birthday = req.getParameter(PARAM_NAME_BIRTHDAY);
        Date birthdayDate = birthday == null || birthday.isEmpty() ? null : Date.valueOf(birthday);

        if (!RegisterLogic.isValidEmail(email) || ProfileLogic.emailExists(userId, email)) {
            req.setAttribute(IS_ERROR_INVALID_EMAIL, ERROR);
            isDataValid = false;
        }
        if (!RegisterLogic.isValidName(firstName)) {
            req.setAttribute(IS_ERROR_INVALID_FIRST_NAME, ERROR);
            isDataValid = false;
        }
        if (!RegisterLogic.isValidName(lastName)) {
            req.setAttribute(IS_ERROR_INVALID_LAST_NAME, ERROR);
            isDataValid = false;
        }
        if (!RegisterLogic.isValidBirthday(birthdayDate)) {
            req.setAttribute(IS_ERROR_INVALID_BIRTHDAY, ERROR);
            isDataValid = false;
        }
        if (!RegisterLogic.isValidPhoneNumber(phoneNumber)) {
            req.setAttribute(IS_ERROR_INVALID_PHONE_NUMBER, ERROR);
            isDataValid = false;
        }
        if (isDataValid && ProfileLogic.editInfo(userId, firstName, lastName, birthdayDate, phoneNumber, email)) {
            req.setAttribute(IS_SUCCESS_EDIT_PROFILE, SUCCESS);
        }
        return page;
    }
}
