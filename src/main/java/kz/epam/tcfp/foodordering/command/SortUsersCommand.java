package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Role;
import kz.epam.tcfp.foodordering.entity.User;
import kz.epam.tcfp.foodordering.logic.ProfileLogic;
import kz.epam.tcfp.foodordering.logic.RoleLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortUsersCommand implements ActionCommand {

    private static final String PATH_PAGE_DELETE_USER = "path.page.delete.user";
    private static final String PARAM_NAME_SORT_BY = "sort_by";
    private static final String SORT_BY_NAME = "name";
    private static final String SORT_BY_BIRTHDAY = "birthday";
    private static final String USERS = "users";
    private static final String ROLES = "roles";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_DELETE_USER);
        String sortBy = req.getParameter(PARAM_NAME_SORT_BY);
        List<Role> roles = RoleLogic.getAll();
        List<User> users = ProfileLogic.getAllCustomers();
        if (SORT_BY_NAME.equalsIgnoreCase(sortBy)) {
            Collections.sort(users);
        } else if (SORT_BY_BIRTHDAY.equalsIgnoreCase(sortBy)) {
            users.sort(Comparator.comparing(User::getBirthday));
        }
        req.setAttribute(ROLES, roles);
        req.setAttribute(USERS, users);
        return page;
    }
}
