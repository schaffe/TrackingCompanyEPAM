/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.kpi.project4.dao.drivers;

import java.util.List;
import ua.kpi.project4.dao.applications.*;
import ua.kpi.project4.dao.cars.*;
import ua.kpi.project4.model.Applications;
import ua.kpi.project4.model.Cars;
import ua.kpi.project4.model.Drivers;

public interface DriversDao {
    List<Drivers> getAll();
    Drivers getById(int id);
    void update(Drivers car);
}
