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

    private ActionFactory() {
        actionMap = new EnumMap<>(Commands.class);
        actionMap.put(Commands.AUTH, new ActionAuth());
        actionMap.put(Commands.LOGOUT, new ActionLogout());
        actionMap.put(Commands.NOACTION, new ActionNoAction());
        actionMap.put(Commands.SHOW_ALL_APPLICATIONS, new ActionShowAllApllications());
        actionMap.put(Commands.VIEW_APPLICATION, new ActionViewApplication());
        actionMap.put(Commands.SHOW_DRIVERS, new ActionShowDrivers());
        actionMap.put(Commands.SET_DRIVER, new ActionSetDriver());
        actionMap.put(Commands.SET_CAR, new ActionSetCar());
        actionMap.put(Commands.SHOW_CARS, new ActionShowCars());
        actionMap.put(Commands.START_TRUCKING, new ActionStartTrucking());

    }

    public Action getAction(HttpServletRequest request) {
        if (request.getParameter(RequestParameters.COMMAND_STR) != null) {
            String action = request.getParameter(RequestParameters.COMMAND_STR);
            Commands actionKey;
            try {
                actionKey = Commands.valueOf(action);
            } catch (IllegalArgumentException e) {
                actionKey = Commands.NOACTION;
            }
            return actionMap.get(actionKey);
        } else {
            return actionMap.get(Commands.NOACTION);
        }
    }
    
    public Action getAction(Commands action) {
            return actionMap.get(action);
    }

    public static ActionFactory getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public static class SingletonHolder {

        public static final ActionFactory HOLDER_INSTANCE = new ActionFactory();
    }
}
