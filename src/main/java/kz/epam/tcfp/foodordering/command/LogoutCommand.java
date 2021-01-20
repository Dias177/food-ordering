package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    private static final String PATH_PAGE_INDEX = "path.page.index";

    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigurationManager.getProperty(PATH_PAGE_INDEX);
        req.getSession().invalidate();
        return page;
    }
}
