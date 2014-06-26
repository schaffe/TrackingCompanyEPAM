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
public class ActionLogout implements Action, SessionParameters {
    public static final String ACTION = "logout";
    public static final String REDIRECT = "./index.jsp";
    
    @Override
    public String execute(View view){
        HttpSession session = view.getRequest().getSession();
        session.invalidate();
        return REDIRECT;
    }

    @Override
    public boolean isForward() {
        return false;
    }
    
    
}
