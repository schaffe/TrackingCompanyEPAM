package ua.kpi.project4.dao.drivers;

import java.util.List;
import ua.kpi.project4.model.Drivers;

public interface DriversDao {

    List<Drivers> getAll();

    Drivers getById(int id);

    Drivers getByUserAccount(int accountId);

    void update(Drivers car);
}
