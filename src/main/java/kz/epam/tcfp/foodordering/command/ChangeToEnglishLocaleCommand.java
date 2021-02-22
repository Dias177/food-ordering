package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Locale;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.LOCALE;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_LOGIN;

public class ChangeToEnglishLocaleCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_LOGIN);
        req.getSession().setAttribute(LOCALE, Locale.US);
        return page;
    }
}
