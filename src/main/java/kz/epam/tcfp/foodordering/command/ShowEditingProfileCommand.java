package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.User;
import kz.epam.tcfp.foodordering.logic.ProfileLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class ShowEditingProfileCommand implements ActionCommand {

    private static final String PATH_PAGE_EDIT_PROFILE = "path.page.edit.profile";
    private static final String CURRENT_FIRST_NAME = "currentFirstName";
    private static final String CURRENT_LAST_NAME = "currentLastName";
    private static final String CURRENT_BIRTHDAY = "currentBirthday";
    private static final String CURRENT_PHONE_NUMBER = "currentPhoneNumber";
    private static final String CURRENT_EMAIL = "currentEmail";
    private static final String USER_ID = "userId";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_EDIT_PROFILE);
        long userId = (long) req.getSession().getAttribute(USER_ID);
        User user = ProfileLogic.getUserById(userId);
        req.setAttribute(CURRENT_FIRST_NAME, user.getFirstName());
        req.setAttribute(CURRENT_LAST_NAME, user.getLastName());
        req.setAttribute(CURRENT_BIRTHDAY, user.getBirthday());
        req.setAttribute(CURRENT_PHONE_NUMBER, user.getPhoneNumber());
        req.setAttribute(CURRENT_EMAIL, user.getEmail());
        return page;
    }
}
