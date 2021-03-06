package kz.epam.tcfp.foodordering.controller;

import kz.epam.tcfp.foodordering.command.ActionCommand;
import kz.epam.tcfp.foodordering.command.factory.ActionFactory;
import kz.epam.tcfp.foodordering.connectionpool.ConnectionPool;
import kz.epam.tcfp.foodordering.dao.DaoException;
import kz.epam.tcfp.foodordering.util.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

import static kz.epam.tcfp.foodordering.util.PathPageConstants.PATH_PAGE_MAIN;

public class FoodOrderingController extends HttpServlet {

    private final ActionFactory actionFactory = ActionFactory.getInstance();
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = null;
        ActionCommand command = actionFactory.getCommand(req);
        try {
            page = command.execute(req);
        } catch (ParseException | DaoException e) {
            logger.error(e);
        }
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        } else {
            page = ConfigurationManager.getProperty(PATH_PAGE_MAIN);
            resp.sendRedirect(req.getContextPath() + page);
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroyPool();
    }
}
