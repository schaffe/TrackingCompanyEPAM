/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author User
 */
public class ActionFactory {
    private final static String COMMAND_STR = "action";
    private final Map<String, Action> actionMap;

    public ActionFactory() {
        actionMap = new HashMap<>();
        actionMap.put(ActionAuth.ACTION, new ActionAuth());
        actionMap.put(ActionLogout.ACTION, new ActionLogout());
    }

    public Action getAction(HttpServletRequest request) {
        return actionMap.get(request.getParameter(COMMAND_STR));
    }
}
