package kz.epam.tcfp.foodordering.command;

import kz.epam.tcfp.foodordering.dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

public interface ActionCommand {
    String execute(HttpServletRequest req) throws ParseException, DaoException;
}
