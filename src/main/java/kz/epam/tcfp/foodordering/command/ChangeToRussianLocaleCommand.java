package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Locale;

public class ChangeToRussianLocaleCommand implements ActionCommand {

    private static final String LOCALE = "locale";
    private static final String LOCALE_LANGUAGE = "ru";
    private static final String LOCALE_COUNTRY = "RU";
    private static final String PATH_PAGE_LOGIN = "path.page.login";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_LOGIN);
        req.getSession().setAttribute(LOCALE, new Locale(LOCALE_LANGUAGE, LOCALE_COUNTRY));
        return page;
    }
}
