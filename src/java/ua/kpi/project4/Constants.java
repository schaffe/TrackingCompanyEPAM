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
    }
    
    public interface SessionParameters {

        String USER_ID = "user_id";
    }

    public interface RequestParameters {

        String LOGIN = "login";
        String PASSWORD = "password";
        String FULLNAME = "fullname";
        String COMMAND_STR = "action";
    }

    public enum Commands {
        NOACTION,
        AUTH,
        LOGOUT,;
    }
    
    String ACTION = "command";
}
