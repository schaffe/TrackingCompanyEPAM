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
import ua.kpi.project4.model.Cars;
import ua.kpi.project4.model.Drivers;

/**
 *
 * @author User
 */
public class ActionShowCars implements Action {

    @Override
    public String execute(View view) {
        HttpServletRequest request = view.getRequest();

        try {

            //filter drivers to some application
            if (request.getParameter(RequestParameters.DRIVER) != null) {
                int driverId = Integer.valueOf(request.getParameter(RequestParameters.DRIVER));
                DaoFactory daoFactory = DaoFactory.getDaoFactory();
                Drivers driver = daoFactory.getDriversDao(daoFactory.getConnection()).getById(driverId);
                List<Cars> cars = daoFactory.getCarsDao(daoFactory.getConnection()).getAll();

                if (request.getParameter(RequestParameters.APPLICATION) != null) {
                    request.setAttribute(RequestParameters.APPLICATION, Integer.valueOf(request.getParameter(RequestParameters.APPLICATION)));
                    
                    for (Iterator<Cars> it = cars.iterator(); it.hasNext();) {
                        Cars car = it.next();
                        if (!car.getIsValid()) {
                            it.remove();
                        }
                    }
                }
                request.setAttribute(RequestParameters.DRIVER, driver);
                request.setAttribute(RequestParameters.LIST, cars);
            }
        } catch (IllegalArgumentException e) {
        }

        return Pages.CARS;
    }
}
