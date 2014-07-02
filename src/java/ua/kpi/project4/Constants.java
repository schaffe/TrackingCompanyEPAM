package ua.kpi.project4;

/**
 * Storing constants.
 *
 * How to use: JSP 1) Import Constants class on JSP page. Example:
 * <code><%@ page import="ua.kpi.project4.Constants" %></code> 2)Use constant
 * Example: <code><%=Constants.SessionParameters.PROFILE_ID%></code>
 *
 * Java 1) Import interface <code>ua.kpi.project4.Constants</code> 2) Use any
 * requiring constant Example: <code>Pages.INDEX</code>
 *
 */
public interface Constants {

    /**
     * Storing names of pages
     */
    public interface Pages {

        String INDEX = "./index.jsp";
        String WELCOME_PAGE = "./welcomePage.jsp";
        String APPLICATIONS = "./applications.jsp";
        String APPLICATION_DETAILS = "./applicationDetails.jsp";
        String DRIVERS = "./drivers.jsp";
        String CARS = "./cars.jsp";
        String TRUCK_FINISHED = "./truckFinished.jsp";
        String CREATE_APPLICATION = "./createApplication.jsp";
    }

    /**
     * Storing names of Session Parameters
     */
    public interface SessionParameters {

        String PROFILE_ID = "profile_id";
        String USER_ID = "user_id";
        String DRIVER_ID = "driver_id";
    }

    /**
     * Storing names of Request Parameters
     */
    public interface RequestParameters {

        String LOGIN = "login";
        String PASSWORD = "password";
        String FULLNAME = "fullname";
        String COMMAND_STR = "action";
        String APPLICATIONS = "applications";
        String APPLICATION = "application";
        String APPLICATION_ACTION = "aaction";
        String ID = "id";
        String LIST = "list";
        String DRIVER = "driver";
        String CAR = "car";
        String FROM = "from";
        String DESTINATION = "to";
        String PLACES = "places";
        String TIME = "time";

    }

    /**
     * Storing names of Commands in enumeration form
     */
    public enum Commands {

        NOACTION,
        AUTH,
        LOGOUT,
        SHOW_ALL_APPLICATIONS,
        VIEW_APPLICATION,
        SHOW_DRIVERS,
        SET_DRIVER,
        SHOW_CARS,
        SET_CAR,
        START_TRUCKING,
        TRUCK_FINISHED,
        REPAIR_CAR,
        CREATE_APPLICATION
    }

    /**
     * Storing names of Profiles in enumeration form
     */
    public enum Profiles {

        DISPATCHER,
        DRIVER,
        CUSTOMER
    }

    /**
     * Storing names of application statuses in enumeration form
     */
    public enum ApplicationStatus {

        PENDING,
        PROCESSED,
        TRUCKING,
        POSTPONED,
        CANCELLED,
        DONE
    }

    String ACTION = "command";
}
