package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Locale;

import static kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants.*;
import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_LOGIN;
import static kz.epam.tcfp.foodordering.util.ValueConstants.LOCALE_COUNTRY_RU;
import static kz.epam.tcfp.foodordering.util.ValueConstants.LOCALE_LANGUAGE_RU;

public class ChangeToRussianLocaleCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_LOGIN);
        req.getSession().setAttribute(LOCALE, new Locale(LOCALE_LANGUAGE_RU, LOCALE_COUNTRY_RU));
        return page;
    }
}
