package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Role;
import kz.epam.tcfp.foodordering.entity.User;
import kz.epam.tcfp.foodordering.logic.ProfileLogic;
import kz.epam.tcfp.foodordering.logic.RoleLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public class ShowDeleteUserCommand implements ActionCommand {

    private static final String USERS = "users";
    private static final String ROLES = "roles";
    private static final String PATH_PAGE_DELETE_USER = "path.page.delete.user";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_DELETE_USER);
        List<User> users = ProfileLogic.getAllCustomers();
        List<Role> roles = RoleLogic.getAll();
        req.setAttribute(USERS, users);
        req.setAttribute(ROLES, roles);
        return page;
    }
}
