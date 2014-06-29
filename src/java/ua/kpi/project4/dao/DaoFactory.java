package ua.kpi.project4.dao;


import java.sql.Connection;
import ua.kpi.project4.dao.applications.ApplicationsDAO;
import ua.kpi.project4.dao.cars.CarsDAO;
import ua.kpi.project4.dao.drivers.DriversDao;
import ua.kpi.project4.dao.useraccounts.UserAccountsDAO;

public abstract class DaoFactory {
    private static DaoFactory currentFactory = new MySqlDaoFactory();
    
    public abstract Connection getConnection();
    public abstract UserAccountsDAO getUserAccountsDAO(Connection c);
    public abstract ApplicationsDAO getApplicationsDAO(Connection c);
    public abstract CarsDAO getCarsDao(Connection c);
    public abstract DriversDao getDriversDao(Connection c);
    
    public synchronized static DaoFactory getDaoFactory() {
        return currentFactory;
    }
}
