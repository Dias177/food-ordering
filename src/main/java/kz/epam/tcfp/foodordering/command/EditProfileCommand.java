package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.logic.ProfileLogic;
import kz.epam.tcfp.foodordering.logic.RegisterLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;
import kz.epam.tcfp.foodordering.util.MessageManager;

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
    private static final String ERROR_INVALID_EMAIL = "errorInvalidEmail";
    private static final String MESSAGE_EMAIL_ERROR = "message.email.error";
    private static final String ERROR_INVALID_FIRST_NAME = "errorInvalidFirstName";
    private static final String MESSAGE_FIRST_NAME_ERROR = "message.firstname.error";
    private static final String ERROR_INVALID_LAST_NAME = "errorInvalidLastName";
    private static final String MESSAGE_LAST_NAME_ERROR = "message.lastname.error";
    private static final String ERROR_INVALID_BIRTHDAY = "errorInvalidBirthday";
    private static final String MESSAGE_BIRTHDAY_ERROR = "message.birthday.error";
    private static final String ERROR_INVALID_PHONE_NUMBER = "errorInvalidPhoneNumber";
    private static final String MESSAGE_PHONE_NUMBER_ERROR = "message.phone.number.error";
    private static final String PATH_PAGE_EDIT_PROFILE = "path.page.edit.profile";
    private static final String SUCCESS_EDIT_PROFILE = "successEditProfile";
    private static final String MESSAGE_EDIT_PROFILE_SUCCESS = "message.edit.profile.success";

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
            req.setAttribute(ERROR_INVALID_EMAIL, MessageManager.getProperty(MESSAGE_EMAIL_ERROR));
            isDataValid = false;
        }
        if (!RegisterLogic.isValidName(firstName)) {
            req.setAttribute(ERROR_INVALID_FIRST_NAME, MessageManager.getProperty(MESSAGE_FIRST_NAME_ERROR));
            isDataValid = false;
        }
        if (!RegisterLogic.isValidName(lastName)) {
            req.setAttribute(ERROR_INVALID_LAST_NAME, MessageManager.getProperty(MESSAGE_LAST_NAME_ERROR));
            isDataValid = false;
        }
        if (!RegisterLogic.isValidBirthday(birthdayDate)) {
            req.setAttribute(ERROR_INVALID_BIRTHDAY, MessageManager.getProperty(MESSAGE_BIRTHDAY_ERROR));
            isDataValid = false;
        }
        if (!RegisterLogic.isValidPhoneNumber(phoneNumber)) {
            req.setAttribute(ERROR_INVALID_PHONE_NUMBER, MessageManager.getProperty(MESSAGE_PHONE_NUMBER_ERROR));
            isDataValid = false;
        }
        if (isDataValid && ProfileLogic.editInfo(userId, firstName, lastName, birthdayDate, phoneNumber, email)) {
            req.setAttribute(SUCCESS_EDIT_PROFILE, MessageManager.getProperty(MESSAGE_EDIT_PROFILE_SUCCESS));
        }
        return page;
    }
}
