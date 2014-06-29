/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ua.kpi.project4.Constants;
import ua.kpi.project4.dao.DaoFactory;
import ua.kpi.project4.model.UserAccounts;

/**
 *
 * @author User
 */
public class ActionAuth implements Action {

    @Override
    public String execute(View view) {
        HttpServletRequest request = view.getRequest();
        String login = request.getParameter(RequestParameters.LOGIN);
        String password = request.getParameter(RequestParameters.PASSWORD);

        try {
            DaoFactory daoFactory = DaoFactory.getDaoFactory();
            UserAccounts account = daoFactory.getUserAccountsDAO(daoFactory.getConnection()).getByLogin(login);
            if (password.equals(account.getPassword())) {
                int userId = account.getUserAccountId();
                String name = account.getFullName();
                request.setAttribute(RequestParameters.FULLNAME, name);
                HttpSession session = request.getSession(true);
                session.setAttribute(SessionParameters.USER_ID, userId);
                session.setAttribute(SessionParameters.PROFILE_ID, account.getProfile());
                return Pages.WELCOME_PAGE;
            }
        } catch (IllegalArgumentException e) {
        }

        return Pages.INDEX;
    }
}
