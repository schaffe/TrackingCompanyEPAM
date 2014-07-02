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
 * In class-based programming, the factory method pattern is a creational
 * pattern which uses factory methods to deal with the problem of creating
 * objects without specifying the exact class of object that will be created.
 * This is done by creating objects via a factory method, which is either
 * specified in an interface (abstract class) and implemented in implementing
 * classes (concrete classes); or implemented in a base class (optionally as a
 * template method), which can be overridden when inherited in derived classes.
 */
public class ActionFactory implements Constants {

    /**
     * Map for cashing actions.
     */
    private final Map<Commands, Action> actionMap;

    /**
     * Constructor initiates factory and action map.
     */
    private ActionFactory() {
        actionMap = new EnumMap<>(Commands.class);
        init();
    }

    private void init() {
        actionMap.put(Commands.AUTH, new ActionAuth());
        actionMap.put(Commands.CREATE_APPLICATION, new ActionCreateApplication());
        actionMap.put(Commands.LOGOUT, new ActionLogout());
        actionMap.put(Commands.NOACTION, new ActionNoAction());
        actionMap.put(Commands.SHOW_ALL_APPLICATIONS, new ActionShowAllApllications());
        actionMap.put(Commands.VIEW_APPLICATION, new ActionViewApplication());
        actionMap.put(Commands.SHOW_DRIVERS, new ActionShowDrivers());
        actionMap.put(Commands.SET_DRIVER, new ActionSetDriver());
        actionMap.put(Commands.SET_CAR, new ActionSetCar());
        actionMap.put(Commands.SHOW_CARS, new ActionShowCars());
        actionMap.put(Commands.START_TRUCKING, new ActionStartTrucking());
        actionMap.put(Commands.TRUCK_FINISHED, new ActionTruckFinished());
    }

    /**
     * Get proper command according to parameter "action" received by request.
     *
     * @param request instance of HttpServletRequest for getting "action"
     * parameter.
     * @return instance of action.
     */
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

    /**
     * Get action by core servlet interface. Used for inner command interaction
     * and redirecting.
     *
     * @param action Enum value of command.
     * @return action.
     */
    public Action getAction(Commands action) {
        return actionMap.get(action);
    }

    /**
     * Get instance of ActionFactory.
     *
     * @return instance of ActionFactory.
     */
    public static ActionFactory getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    private static class SingletonHolder {

        static final ActionFactory HOLDER_INSTANCE = new ActionFactory();
    }
}
