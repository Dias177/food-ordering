package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_INDEX;

public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest req) {
        String page = ConfigurationManager.getProperty(PATH_PAGE_INDEX);
        req.getSession().invalidate();
        return page;
    }
}
