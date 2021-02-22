package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.User;
import kz.epam.tcfp.foodordering.logic.ProfileLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_EDIT_PROFILE;

public class ShowEditingProfileCommand implements ActionCommand {

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
