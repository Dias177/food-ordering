package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.User;
import kz.epam.tcfp.foodordering.logic.RegisterLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;
import kz.epam.tcfp.foodordering.util.PasswordHashing;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class RegisterCommand implements ActionCommand{

    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_CONFIRMED_PASSWORD = "confirmedPassword";
    private static final String PARAM_NAME_FIRST_NAME = "firstName";
    private static final String PARAM_NAME_LAST_NAME = "lastName";
    private static final String PARAM_NAME_PHONE_NUMBER = "phoneNumber";
    private static final String PARAM_NAME_BIRTHDAY = "birthday";
    private static final String IS_ERROR_INVALID_EMAIL = "isErrorInvalidEmail";
    private static final String IS_ERROR_INVALID_PASSWORD = "isErrorInvalidPassword";
    private static final String IS_ERROR_WRONG_CONFIRMED_PASSWORD = "isErrorWrongConfirmedPassword";
    private static final String IS_ERROR_INVALID_FIRST_NAME = "isErrorInvalidFirstName";
    private static final String IS_ERROR_INVALID_LAST_NAME = "isErrorInvalidLastName";
    private static final String IS_ERROR_INVALID_BIRTHDAY = "isErrorInvalidBirthday";
    private static final String IS_ERROR_INVALID_PHONE_NUMBER = "isErrorInvalidPhoneNumber";
    private static final String IS_SUCCESS_REGISTRATION = "isSuccessRegistration";
    private static final String PATH_PAGE_REGISTRATION = "path.page.registration";
    private static final boolean SUCCESS = true;
    private static final boolean ERROR = true;

    @Override
    public String execute(HttpServletRequest req) throws DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_REGISTRATION);
        boolean isDataValid = true;
        String email = req.getParameter(PARAM_NAME_EMAIL);
        String password = req.getParameter(PARAM_NAME_PASSWORD);
        String confirmedPassword = req.getParameter(PARAM_NAME_CONFIRMED_PASSWORD);
        String firstName = req.getParameter(PARAM_NAME_FIRST_NAME);
        String lastName = req.getParameter(PARAM_NAME_LAST_NAME);
        String phoneNumber = req.getParameter(PARAM_NAME_PHONE_NUMBER);
        String birthday = req.getParameter(PARAM_NAME_BIRTHDAY);
        Date birthdayDate = birthday == null || birthday.isEmpty() ? null : Date.valueOf(birthday);

        if (!RegisterLogic.isValidEmail(email) || RegisterLogic.emailExists(email)) {
            req.setAttribute(IS_ERROR_INVALID_EMAIL, ERROR);
            isDataValid = false;
        }
        if (!RegisterLogic.isValidPassword(password)) {
            req.setAttribute(IS_ERROR_INVALID_PASSWORD, ERROR);
            isDataValid = false;
        }
        if (!RegisterLogic.isCorrectConfirmedPassword(confirmedPassword, password)) {
            req.setAttribute(IS_ERROR_WRONG_CONFIRMED_PASSWORD, ERROR);
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
        if (isDataValid) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(PasswordHashing.hash(password));
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phoneNumber);
            user.setBirthday(birthdayDate);
            RegisterLogic.register(user);
            req.setAttribute(IS_SUCCESS_REGISTRATION, SUCCESS);
        }
        return page;
    }
}
