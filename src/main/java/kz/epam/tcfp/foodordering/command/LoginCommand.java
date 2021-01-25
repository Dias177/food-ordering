package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Food;
import kz.epam.tcfp.foodordering.logic.LoginLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;
import kz.epam.tcfp.foodordering.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

public class LoginCommand implements ActionCommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String USER_ID = "userId";
    private static final String USER_ROLE = "userRole";
    private static final String CART = "cart";
    private static final String ERROR_LOGIN_PASS_MESSAGE = "errorLoginPassMessage";
    private static final String PATH_PAGE_MAIN = "path.page.main";
    private static final String PATH_PAGE_LOGIN = "path.page.login";
    private static final String MESSAGE_LOGIN_ERROR = "message.login.error";

    @Override
    public String execute(HttpServletRequest req) throws DaoException {
        String page;
        String login = req.getParameter(PARAM_NAME_LOGIN);
        String password = req.getParameter(PARAM_NAME_PASSWORD);
        if (LoginLogic.checkLogin(login, password)) {
            req.getSession().setAttribute(USER_ID, LoginLogic.getUserId(login));
            req.getSession().setAttribute(USER_ROLE, LoginLogic.getUserRoleName(login, password).toUpperCase());
            req.getSession().setAttribute(CART, new HashSet<Food>());
            page = ConfigurationManager.getProperty(PATH_PAGE_MAIN);
        } else {
            req.setAttribute(ERROR_LOGIN_PASS_MESSAGE, MessageManager.getProperty(MESSAGE_LOGIN_ERROR));
            page = ConfigurationManager.getProperty(PATH_PAGE_LOGIN);
        }
        return page;
    }
}