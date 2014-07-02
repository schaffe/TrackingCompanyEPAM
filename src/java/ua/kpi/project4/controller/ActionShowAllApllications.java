/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.controller;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import ua.kpi.project4.Constants;
import ua.kpi.project4.dao.DaoFactory;
import ua.kpi.project4.model.Applications;

/**
 * Action is used for output list of application. Applications are selected in
 * according to user`s profile i.e. drivers will receive list of his trucks,
 * customer will receive list of his applications.
 */
public class ActionShowAllApllications implements Action {

    @Override
    public String execute(View view) {
        HttpServletRequest request = view.getRequest();

        try {
            DaoFactory daoFactory = DaoFactory.getDaoFactory();
            List<Applications> applications = daoFactory.getApplicationsDAO(daoFactory.getConnection()).getAllApplications();

            String profile = request.getSession().getAttribute(SessionParameters.PROFILE_ID).toString();
            Constants.Profiles profileEnum = Constants.Profiles.valueOf(profile);
            int userId = Integer.parseInt(request.getSession().getAttribute(SessionParameters.USER_ID).toString());

            switch (profileEnum) {
                case DRIVER:
                    for (Iterator<Applications> it = applications.iterator(); it.hasNext();) {
                        Applications application = it.next();
                        if (application.getDriver() == null) {
                            it.remove();
                            continue;
                        }
                        if (application.getDriver().getUserAccount().getUserAccountId() != userId) {
                            it.remove();
                            continue;
                        }
                    }
                    break;

                case CUSTOMER:
                    for (Iterator<Applications> it = applications.iterator(); it.hasNext();) {
                        Applications application = it.next();
                        if (application.getCreatorUserAccount().getUserAccountId() != userId) {
                            it.remove();
                        }
                    }
                    break;
            }

            request.setAttribute(RequestParameters.APPLICATIONS, applications);
        } catch (IllegalArgumentException e) {
        }

        return Pages.APPLICATIONS;
    }
}
