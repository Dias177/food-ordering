package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.User;
import kz.epam.tcfp.foodordering.logic.ProfileLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_DELETE_USER;
import static kz.epam.tcfp.foodordering.util.ValueConstants.SORT_BY_BIRTHDAY;
import static kz.epam.tcfp.foodordering.util.ValueConstants.SORT_BY_NAME;

public class SortUsersCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_DELETE_USER);
        String sortBy = req.getParameter(PARAM_NAME_SORT_BY);
        List<User> users = ProfileLogic.getAllCustomers();
        if (SORT_BY_NAME.equalsIgnoreCase(sortBy)) {
            Collections.sort(users);
        } else if (SORT_BY_BIRTHDAY.equalsIgnoreCase(sortBy)) {
            users.sort(Comparator.comparing(User::getBirthday,
                    Comparator.nullsFirst(Comparator.naturalOrder())));
        }
        req.setAttribute(USERS, users);
        return page;
    }
}
