/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.controller;

import javax.servlet.http.HttpServletRequest;
import ua.kpi.project4.dao.DaoFactory;
import ua.kpi.project4.model.Cars;
import ua.kpi.project4.model.Drivers;

/**
 *
 * @author User
 */
public class ActionSetCar implements Action {

    @Override
    public String execute(View view) {
        HttpServletRequest request = view.getRequest();

        try {
            if (request.getParameter(RequestParameters.CAR) != null && request.getParameter(RequestParameters.DRIVER) != null) {
                int carId = Integer.valueOf(request.getParameter(RequestParameters.CAR));
                int driverId = Integer.valueOf(request.getParameter(RequestParameters.DRIVER));
                DaoFactory daoFactory = DaoFactory.getDaoFactory();
                Drivers driver = daoFactory.getDriversDao(daoFactory.getConnection()).getById(driverId);
                Cars car = daoFactory.getCarsDao(daoFactory.getConnection()).getById(carId);
                
                if (car.getIsValid()) {
                    driver.setCar(car);
                    daoFactory.getDriversDao(daoFactory.getConnection()).update(driver);
                } else {
                    //TODO error!
                }
            }
            

        } catch (IllegalArgumentException e) {
        }

        return (new ActionShowDrivers()).execute(view);
    }
}
