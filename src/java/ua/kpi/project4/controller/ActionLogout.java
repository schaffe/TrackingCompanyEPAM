/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.controller;

import javax.servlet.http.HttpSession;

/**
 * Destroys current session and redirects to login page
 */
public class ActionLogout implements Action {

    @Override
    public String execute(View view) {
        HttpSession session = view.getRequest().getSession();
        session.invalidate();
        return Pages.INDEX;
    }
}
