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
public class ActionShowDrivers implements Action {

    @Override
    public String execute(View view) {
        HttpServletRequest request = view.getRequest();

        try {
            DaoFactory daoFactory = DaoFactory.getDaoFactory();
            List<Drivers> drivers = daoFactory.getDriversDao(daoFactory.getConnection()).getAll();
            
            //filter drivers to some application
            if(request.getParameter(RequestParameters.APPLICATION) != null) {
                int applicationId = Integer.valueOf(request.getParameter(RequestParameters.APPLICATION));
                Applications application = daoFactory.getApplicationsDAO(daoFactory.getConnection()).getApplicationsById(applicationId);
                
                for (Iterator<Drivers> it = drivers.iterator(); it.hasNext();) {
                    Drivers driver = it.next();
                    Cars car = driver.getCar();
                    if (application.getPassengersNum() > driver.getCar().getPlacesNumber()) {
                        it.remove();
                    }
                }
            }
            
            request.setAttribute(RequestParameters.LIST, drivers);
        } catch (IllegalArgumentException e) {
        }

        return Pages.DRIVERS;
    }
}
