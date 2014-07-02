/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.controller;

import javax.servlet.http.HttpServletRequest;
import ua.kpi.project4.dao.DaoFactory;
import ua.kpi.project4.model.Applications;

/**
 * Action is used for displaying application.
 */
public class ActionViewApplication implements Action {

    @Override
    public String execute(View view) {
        HttpServletRequest request = view.getRequest();
        int applicationId = Integer.parseInt(request.getParameter(RequestParameters.ID));
        try {
            DaoFactory daoFactory = DaoFactory.getDaoFactory();
            Applications application = daoFactory.getApplicationsDAO(daoFactory.getConnection()).getApplicationsById(applicationId);
            request.setAttribute(RequestParameters.APPLICATION, application);
        } catch (IllegalArgumentException e) {
        }

        return Pages.APPLICATION_DETAILS;
    }
}
