package ua.kpi.project4.dao;

import java.sql.Connection;
import ua.kpi.project4.dao.applications.ApplicationsDAO;
import ua.kpi.project4.dao.cars.CarsDAO;
import ua.kpi.project4.dao.drivers.DriversDao;
import ua.kpi.project4.dao.useraccounts.UserAccountsDAO;

/**
 * Data access object (DAO) is an object that provides an abstract interface to
 * some type of database or other persistence mechanism. By mapping application
 * calls to the persistence layer, DAO provide some specific data operations
 * without exposing details of the database.
 */
public abstract class DaoFactory {

    /**
     * Instance of current DBMS DAO factory.
     */
    private static DaoFactory currentFactory = new MySqlDaoFactory();

    /**
     * Return instance of connection to the DB.
     */
    public abstract Connection getConnection();

    /**
     * General interface to get UserAccounts DAO
     *
     * @param c - instance of DB connection
     * @return UserAccounts DAO
     */
    public abstract UserAccountsDAO getUserAccountsDAO(Connection c);

    /**
     * General interface to get Applications DAO
     *
     * @param c - instance of DB connection
     * @return Applications DAO
     */
    public abstract ApplicationsDAO getApplicationsDAO(Connection c);

    /**
     * General interface to get Cars DAO
     *
     * @param c - instance of DB connection
     * @return Cars DAO
     */
    public abstract CarsDAO getCarsDao(Connection c);

    /**
     * General interface to get Drivers DAO
     *
     * @param c - instance of DB connection
     * @return Drivers DAO
     */
    public abstract DriversDao getDriversDao(Connection c);

    public synchronized static DaoFactory getDaoFactory() {
        return currentFactory;
    }
}
