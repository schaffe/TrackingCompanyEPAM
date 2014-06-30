package ua.kpi.project4.dao.cars;

import java.util.List;
import ua.kpi.project4.model.Cars;

public interface CarsDAO {
    List<Cars> getAll();
    Cars getById(int id);
    void update(Cars car);
}
