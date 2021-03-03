package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.entity.Food;
import kz.epam.tcfp.foodordering.logic.LoginLogic;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_LOGIN;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_MAIN;
import static kz.epam.tcfp.foodordering.util.ValueConstants.ERROR;

public class LoginCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws DaoException {
        String page;
        String login = req.getParameter(PARAM_NAME_LOGIN);
        String password = req.getParameter(PARAM_NAME_PASSWORD);
        if (LoginLogic.checkLogin(login, password)) {
            req.getSession().setAttribute(USER_ID, LoginLogic.getUserId(login));
            req.getSession().setAttribute(USER_ROLE, LoginLogic.getUserRoleName(login).toUpperCase());
            req.getSession().setAttribute(CART, new HashSet<Food>());
            page = ConfigurationManager.getProperty(PATH_PAGE_MAIN);
        } else {
            req.setAttribute(IS_ERROR_LOGIN_PASS_MESSAGE, ERROR);
            page = ConfigurationManager.getProperty(PATH_PAGE_LOGIN);
        }
        return page;
    }
}
