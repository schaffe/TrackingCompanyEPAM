/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.controller;

import java.util.EnumMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import ua.kpi.project4.Constants;

/**
 *
 * @author User
 */
public class ActionFactory implements Constants {

    private final Map<Commands, Action> actionMap;

    public ActionFactory() {
        actionMap = new EnumMap<>(Commands.class);
        actionMap.put(Commands.AUTH, new ActionAuth());
        actionMap.put(Commands.LOGOUT, new ActionLogout());
        actionMap.put(Commands.NOACTION, new ActionNoAction());
    }

    public Action getAction(HttpServletRequest request) {
        String action = request.getParameter(RequestParameters.COMMAND_STR);
        if (action.isEmpty()) {
            action = Commands.NOACTION.name();
        }
        return actionMap.get(Commands.valueOf(action));
    }
}
