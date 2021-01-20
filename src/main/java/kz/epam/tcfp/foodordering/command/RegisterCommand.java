package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.User;
import kz.epam.tcfp.foodordering.logic.RegisterLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;
import kz.epam.tcfp.foodordering.util.MessageManager;
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
    private static final String ERROR_INVALID_EMAIL = "errorInvalidEmail";
    private static final String MESSAGE_EMAIL_ERROR = "message.email.error";
    private static final String ERROR_INVALID_PASSWORD = "errorInvalidPassword";
    private static final String MESSAGE_PASSWORD_ERROR = "message.password.error";
    private static final String ERROR_WRONG_CONFIRMED_PASSWORD = "errorWrongConfirmedPassword";
    private static final String MESSAGE_CONFIRMED_PASSWORD_ERROR = "message.confirmed.password.error";
    private static final String ERROR_INVALID_FIRST_NAME = "errorInvalidFirstName";
    private static final String MESSAGE_FIRST_NAME_ERROR = "message.firstname.error";
    private static final String ERROR_INVALID_LAST_NAME = "errorInvalidLastName";
    private static final String MESSAGE_LAST_NAME_ERROR = "message.lastname.error";
    private static final String ERROR_INVALID_BIRTHDAY = "errorInvalidBirthday";
    private static final String MESSAGE_BIRTHDAY_ERROR = "message.birthday.error";
    private static final String ERROR_INVALID_PHONE_NUMBER = "errorInvalidPhoneNumber";
    private static final String MESSAGE_PHONE_NUMBER_ERROR = "message.phone.number.error";
    private static final String PATH_PAGE_INDEX = "path.page.index";
    private static final String PATH_PAGE_REGISTRATION = "path.page.registration";

    @Override
    public String execute(HttpServletRequest req) throws DaoException {
        String page;
        boolean isDataValid = true;
        String email = req.getParameter(PARAM_NAME_EMAIL);
        String password = req.getParameter(PARAM_NAME_PASSWORD);
        String confirmedPassword = req.getParameter(PARAM_NAME_CONFIRMED_PASSWORD);
        String firstName = req.getParameter(PARAM_NAME_FIRST_NAME);
        String lastName = req.getParameter(PARAM_NAME_LAST_NAME);
        String phoneNumber = req.getParameter(PARAM_NAME_PHONE_NUMBER);
        String birthday = req.getParameter(PARAM_NAME_BIRTHDAY);
        Date birthdayDate = Date.valueOf(birthday);

        if (!RegisterLogic.isValidEmail(email) || RegisterLogic.emailExists(email)) {
            req.setAttribute(ERROR_INVALID_EMAIL, MessageManager.getProperty(MESSAGE_EMAIL_ERROR));
            isDataValid = false;
        }
        if (!RegisterLogic.isValidPassword(password)) {
            req.setAttribute(ERROR_INVALID_PASSWORD, MessageManager.getProperty(MESSAGE_PASSWORD_ERROR));
            isDataValid = false;
        }
        if (!RegisterLogic.isCorrectConfirmedPassword(confirmedPassword, password)) {
            req.setAttribute(ERROR_WRONG_CONFIRMED_PASSWORD, MessageManager.getProperty(MESSAGE_CONFIRMED_PASSWORD_ERROR));
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
        if (isDataValid) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(PasswordHashing.hash(password));
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPhoneNumber(phoneNumber);
            user.setBirthday(birthdayDate);
            RegisterLogic.register(user);
            page = ConfigurationManager.getProperty(PATH_PAGE_INDEX);
        } else {
            page = ConfigurationManager.getProperty(PATH_PAGE_REGISTRATION);
        }
        return page;
    }
}
