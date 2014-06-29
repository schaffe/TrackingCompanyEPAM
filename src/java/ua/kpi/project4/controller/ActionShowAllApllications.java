/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import ua.kpi.project4.dao.DaoFactory;
import ua.kpi.project4.model.Applications;

/**
 *
 * @author User
 */
public class ActionShowAllApllications implements Action {

    @Override
    public String execute(View view) {
        HttpServletRequest request = view.getRequest();

        try {
            DaoFactory daoFactory = DaoFactory.getDaoFactory();
            List<Applications> applications = daoFactory.getApplicationsDAO(daoFactory.getConnection()).getAllApplications();
            request.setAttribute(RequestParameters.APPLICATIONS, applications);
        } catch (IllegalArgumentException e) {
        }

        return Pages.APPLICATIONS;
    }
}
