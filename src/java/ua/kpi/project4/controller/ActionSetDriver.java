/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.controller;

import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import ua.kpi.project4.dao.DaoFactory;
import ua.kpi.project4.model.Applications;
import ua.kpi.project4.model.Cars;
import ua.kpi.project4.model.Drivers;

/**
 *
 * @author User
 */
public class ActionSetDriver implements Action {

    @Override
    public String execute(View view) {
        HttpServletRequest request = view.getRequest();

        try {
            if (request.getParameter(RequestParameters.APPLICATION) != null && request.getParameter(RequestParameters.DRIVER) != null) {
                int applicationId = Integer.valueOf(request.getParameter(RequestParameters.APPLICATION));
                int driverId = Integer.valueOf(request.getParameter(RequestParameters.DRIVER));
                DaoFactory daoFactory = DaoFactory.getDaoFactory();
                Drivers driver = daoFactory.getDriversDao(daoFactory.getConnection()).getById(driverId);
                Applications application = daoFactory.getApplicationsDAO(daoFactory.getConnection()).getApplicationsById(applicationId);
                Cars car = driver.getCar();
                
                if (car.getIsValid() && application.getPassengersNum() <= car.getPlacesNumber()) {
                    application.setDriver(driver);
                    application.setStatus(ApplicationStatus.TRUCKING.name());
                    daoFactory.getApplicationsDAO(daoFactory.getConnection()).updateApplications(application);
                } else {
                    //TODO error!
                }
                
                request.setAttribute(RequestParameters.APPLICATION, application);
            }

        } catch (IllegalArgumentException e) {
        }

        return Pages.APPLICATION_DETAILS;
    }
}
