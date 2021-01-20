package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.User;
import kz.epam.tcfp.foodordering.logic.ProfileLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public class ShowMyProfileCommand implements ActionCommand {

    private static final String USER = "user";
    private static final String ROLE_NAME = "roleName";
    private static final String USER_ID = "userId";
    private static final String USER_ROLE = "userRole";
    private static final String PATH_PAGE_MY_PROFILE = "path.page.my.profile";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_MY_PROFILE);
        long userId = (long) req.getSession().getAttribute(USER_ID);
        User user = ProfileLogic.getUserById(userId);
        String roleName = (String) req.getSession().getAttribute(USER_ROLE);
        req.setAttribute(USER, user);
        req.setAttribute(ROLE_NAME, roleName);
        return page;
    }
}
