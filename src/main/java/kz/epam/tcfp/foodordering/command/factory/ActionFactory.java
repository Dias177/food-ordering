package kz.epam.tcfp.foodordering.command.factory;

import kz.epam.tcfp.foodordering.command.ActionCommand;
import kz.epam.tcfp.foodordering.util.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    private static final String PARAM_NAME_COMMAND = "command";
    private static final String WRONG_ACTION = "wrongAction";
    private static final String MESSAGE_WRONG_ACTION = "message.wrong.action";
    private static final ActionFactory ACTION_FACTORY = new ActionFactory();

    private ActionFactory() {}

    public ActionCommand getCommand(HttpServletRequest req) {
        ActionCommand command = null;
        String action = req.getParameter(PARAM_NAME_COMMAND);
        if (action == null || action.isEmpty()) {
            return null;
        }
        try {
            command = CommandEnum.valueOf(action.toUpperCase()).getCommand();
        } catch (IllegalArgumentException e) {
            req.setAttribute(WRONG_ACTION, action + MessageManager.getProperty(MESSAGE_WRONG_ACTION));
        }
        return command;
    }

    public static ActionFactory getInstance() {
        return ACTION_FACTORY;
    }
}
