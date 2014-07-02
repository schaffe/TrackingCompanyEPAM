/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import ua.kpi.project4.Constants;
import ua.kpi.project4.dao.DaoFactory;
import ua.kpi.project4.model.Applications;
import ua.kpi.project4.model.UserAccounts;

/**
 * Command represents action of creation of application.
 */
public class ActionCreateApplication implements Action {

    private final String patter_date = "yyyy-MM-dd";

    @Override
    public String execute(View view) {
        HttpServletRequest request = view.getRequest();

        try {

            //filter drivers to some application
            if (request.getParameter(RequestParameters.FROM) != null
                    && request.getParameter(RequestParameters.DESTINATION) != null
                    && request.getParameter(RequestParameters.TIME) != null
                    && request.getParameter(RequestParameters.PLACES) != null) {

                HttpSession session = request.getSession();
                int creatorId = Integer.valueOf(session.getAttribute(SessionParameters.USER_ID).toString());
                String from = request.getParameter(RequestParameters.FROM);
                String dest = request.getParameter(RequestParameters.DESTINATION);
                String date = request.getParameter(RequestParameters.TIME);
                Date time = parseDate(request.getParameter(RequestParameters.TIME));
                int passNum = Integer.valueOf(request.getParameter(RequestParameters.PLACES));

                Applications application = new Applications(new UserAccounts(creatorId), from, dest, time, passNum);
                DaoFactory daoFactory = DaoFactory.getDaoFactory();
                daoFactory.getApplicationsDAO(daoFactory.getConnection()).insertApplications(application);
            }
        } catch (IllegalArgumentException e) {
        }

        return ActionFactory.getInstance().getAction(Constants.Commands.SHOW_ALL_APPLICATIONS).execute(view);
    }

    private Date parseDate(String date) {
        DateFormat df = new SimpleDateFormat(patter_date);
        try {
            return df.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(ActionCreateApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Date();
    }
}
