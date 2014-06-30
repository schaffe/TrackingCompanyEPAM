/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.controller;

import javax.servlet.http.HttpServletRequest;
import ua.kpi.project4.dao.DaoFactory;
import ua.kpi.project4.model.Applications;
import ua.kpi.project4.model.Cars;
import ua.kpi.project4.model.Drivers;

/**
 *
 * @author User
 */
public class ActionTruckFinished implements Action {

    @Override
    public String execute(View view) {
        HttpServletRequest request = view.getRequest();

        try {
            if (request.getParameter(RequestParameters.APPLICATION) != null && request.getParameter(RequestParameters.CAR) != null) {
                int applicationId = Integer.valueOf(request.getParameter(RequestParameters.APPLICATION));
                boolean carState = Boolean.parseBoolean(request.getParameter(RequestParameters.CAR));
                Integer driverId = (Integer) request.getSession().getAttribute(SessionParameters.DRIVER_ID);
                DaoFactory daoFactory = DaoFactory.getDaoFactory();
//                Drivers driver = daoFactory.getDriversDao(daoFactory.getConnection()).getById(driverId);
                Applications application = daoFactory.getApplicationsDAO(daoFactory.getConnection()).getApplicationsById(applicationId);
                Cars car = application.getDriver().getCar();

                if (application.getDriver().getDriverId().equals(driverId)) {
                    application.setStatus(ApplicationStatus.DONE.name());
                    daoFactory.getApplicationsDAO(daoFactory.getConnection()).updateApplications(application);
                    car.setIsValid(carState);
                    daoFactory.getCarsDao(daoFactory.getConnection()).update(car);
                }
            }

        } catch (IllegalArgumentException e) {
        }

        return ActionFactory.getInstance().getAction(Commands.SHOW_ALL_APPLICATIONS).execute(view);
    }
}
