/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4;

/**
 *
 * @author User
 */
public interface Constants {

    public interface Pages {

        String INDEX = "./index.jsp";
        String WELCOME_PAGE = "./welcomePage.jsp";
        String APPLICATIONS = "./applications.jsp";
        String APPLICATION_DETAILS = "./applicationDetails.jsp";
        String DRIVERS = "./drivers.jsp";
        String CARS = "./cars.jsp";

    }

    public interface SessionParameters {

        String PROFILE_ID = "profile_id";
        String USER_ID = "user_id";
    }

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

    }

    public enum Commands {

        NOACTION,
        AUTH,
        LOGOUT,
        SHOW_ALL_APPLICATIONS,
        VIEW_APPLICATION,
        SHOW_DRIVERS,
        SET_DRIVER,
        SHOW_CARS,
        SET_CAR
    }

    public enum Profiles {

        DISPATCHER,
        DRIVER,
        CUSTOMER
    }

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
