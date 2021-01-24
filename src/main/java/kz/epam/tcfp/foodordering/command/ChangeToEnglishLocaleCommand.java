package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Locale;

public class ChangeToEnglishLocaleCommand implements ActionCommand {

    private static final String LOCALE = "locale";
    private static final String PATH_PAGE_LOGIN = "path.page.login";

    @Override
    public String execute(HttpServletRequest req) throws ParseException, DaoException {
        String page = ConfigurationManager.getProperty(PATH_PAGE_LOGIN);
        req.getSession().setAttribute(LOCALE, Locale.US);
        return page;
    }
}
