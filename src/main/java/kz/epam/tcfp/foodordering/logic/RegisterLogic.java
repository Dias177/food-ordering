package kz.epam.tcfp.foodordering.logic;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.dao.EntityTransaction;
import kz.epam.tcfp.foodordering.dao.UserDao;
import kz.epam.tcfp.foodordering.entity.User;
import kz.epam.tcfp.foodordering.util.RegexManager;

import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterLogic {

    private static final String REGEX_EMAIL = "regex.email";
    private static final String REGEX_NAME = "regex.name";
    private static final String REGEX_PASSWORD = "regex.password";
    private static final String REGEX_PHONE_NUMBER = "regex.phone.number";

    private static final UserDao userDao = new UserDao();
    private static final EntityTransaction transaction = new EntityTransaction();

    private RegisterLogic() {
        throw new IllegalStateException("Logic utility class");
    }

    public static void register(User user) throws DaoException {
        transaction.initTransaction(userDao);
        try {
            userDao.create(user);
            transaction.commit();
        } catch (DaoException e) {
            transaction.rollback();
            throw new DaoException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    public static boolean isEmailValid(String email) {
        if (isNullOrEmpty(email)) {
            return false;
        }
        String emailRegex = RegexManager.getProperty(REGEX_EMAIL);
        Pattern emailPattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.matches();
    }

    public static boolean emailExists(String email) throws DaoException {
        User user;
        transaction.init(userDao);
        try {
            user = userDao.findUserByEmail(email);
        } catch (DaoException e) {
            throw new DaoException(e);
        } finally {
            transaction.end();
        }
        return user.getEmail() != null;
    }

    public static boolean isNameValid(String name) {
        if (isNullOrEmpty(name)) {
            return false;
        }
        String nameRegex = RegexManager.getProperty(REGEX_NAME);
        Pattern namePattern = Pattern.compile(nameRegex, Pattern.CASE_INSENSITIVE);
        Matcher nameMatcher = namePattern.matcher(name);
        return nameMatcher.matches();
    }

    public static boolean isPasswordValid(String password) {
        if (isNullOrEmpty(password)) {
            return false;
        }
        String passwordRegex = RegexManager.getProperty(REGEX_PASSWORD);
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        return passwordMatcher.matches();
    }

    public static boolean isConfirmedPasswordCorrect(String password, String confirmedPassword) {
        if (isNullOrEmpty(password) || isNullOrEmpty(confirmedPassword)) {
            return false;
        }
        return password.equals(confirmedPassword);
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        if (isNullOrEmpty(phoneNumber)) {
            return false;
        }
        String phoneNumberRegex = RegexManager.getProperty(REGEX_PHONE_NUMBER);
        Pattern phoneNumberPattern = Pattern.compile(phoneNumberRegex);
        Matcher phoneNumberMatcher = phoneNumberPattern.matcher(phoneNumber);
        return phoneNumberMatcher.matches();
    }

    public static boolean isBirthdayValid(Date birthday) {
        if (birthday == null) {
            return true;
        }
        java.util.Date currentDate = new java.util.Date();
        return birthday.before(currentDate);
    }

    private static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }
}
