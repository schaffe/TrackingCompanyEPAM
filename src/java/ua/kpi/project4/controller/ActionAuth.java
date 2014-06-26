/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.kpi.project4.controller;

import javax.servlet.ServletRequest;

/**
 *
 * @author User
 */
public class ActionAuth implements Action {
    public static final String ACTION = "auth";
    public static final String REDIRECT = "/welcomePage.jsp";
    
    @Override
    public String execute(ServletRequest request){
        request.setAttribute("login", "test2");
        return REDIRECT;
    }
    
    
}
