/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ua.kpi.project4.dao.DaoFactory;
import ua.kpi.project4.model.UserAccounts;

/**
 *
 * @author User
 */
public class ActionAuth implements Action, SessionParameters {

    public static final String ACTION = "auth";
    public static final String REDIRECT = "./welcomePage.jsp";

    @Override
    public String execute(View view) {
        HttpServletRequest request = view.getRequest();
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            DaoFactory daoFactory = DaoFactory.getDaoFactory();
            UserAccounts account = daoFactory.getUserAccountsDAO(daoFactory.getConnection()).getByLogin(login);
            if (password.equals(account.getPassword())) {
                int userId = account.getUserAccountId();
                String name = account.getFullName();
                request.setAttribute("name", name);
                HttpSession session = request.getSession(true);
                session.setAttribute(USER_ID, new Integer(userId));
                return REDIRECT;
            }
        } catch (IllegalArgumentException e) {
        }

        return "./index.jsp";
    }

    @Override
    public boolean isForward() {
        return true;
    }

}
