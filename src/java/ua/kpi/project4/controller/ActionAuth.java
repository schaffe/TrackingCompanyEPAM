/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.kpi.project4.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class ActionAuth implements Action, SessionParameters {
    public static final String ACTION = "auth";
    public static final String REDIRECT = "./welcomePage.jsp";
    
    @Override
    public String execute(View view){
        int userId = 0;
        
        HttpServletRequest request = view.getRequest();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        HttpSession session = request.getSession(true);
        session.setAttribute(USER_ID, new Integer(userId));
        return REDIRECT;
    }

    @Override
    public boolean isForward() {
        return true;
    }
    
    
}
