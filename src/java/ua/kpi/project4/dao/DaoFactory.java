package ua.kpi.project4.dao;


import java.sql.Connection;
import ua.kpi.project4.dao.applications.ApplicationsDAO;
import ua.kpi.project4.dao.useraccounts.UserAccountsDAO;

public abstract class DaoFactory {
    private static DaoFactory currentFactory = new MySqlDaoFactory();
    
    public abstract Connection getConnection();
    public abstract UserAccountsDAO getUserAccountsDAO(Connection c);
    public abstract ApplicationsDAO getApplicationsDAO(Connection c);
    
    public static DaoFactory getDaoFactory() {
        return currentFactory;
    }
}
