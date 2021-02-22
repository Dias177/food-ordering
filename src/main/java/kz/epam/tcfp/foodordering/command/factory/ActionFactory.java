package kz.epam.tcfp.foodordering.command.factory;

import kz.epam.tcfp.foodordering.command.ActionCommand;
import kz.epam.tcfp.foodordering.util.ParamAndAttrNameConstants;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    private static final ActionFactory ACTION_FACTORY = new ActionFactory();

    private ActionFactory() {}

    public ActionCommand getCommand(HttpServletRequest req) {
        ActionCommand command;
        String action = req.getParameter(ParamAndAttrNameConstants.PARAM_NAME_COMMAND);
        if (action == null || action.isEmpty()) {
            return null;
        }
        command = CommandEnum.valueOf(action.toUpperCase()).getCommand();
        return command;
    }

    public static ActionFactory getInstance() {
        return ACTION_FACTORY;
    }
}
