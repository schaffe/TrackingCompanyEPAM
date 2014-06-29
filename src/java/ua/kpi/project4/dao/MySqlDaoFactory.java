package ua.kpi.project4.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import ua.kpi.project4.dao.applications.ApplicationsDAO;
import ua.kpi.project4.dao.applications.MySqlApplicationsDaoImpl;
import ua.kpi.project4.dao.useraccounts.MySqlUserAccountsDaoImpl;
import ua.kpi.project4.dao.useraccounts.UserAccountsDAO;

/**
 * MySqlDaoFactory class.
 *
 * @author Artur Dzidzoiev
 * @version May 21, 2014
 */
public class MySqlDaoFactory extends DaoFactory {

    public static final String CONTEXT = "Trucking_company";

    /**
     * Connection pool using GlassFish Connection Pool
     */
    private DataSource pool;

    /**
     * Create connection with data base or getting already created
     *
     * @return Connection instance
     */
    @Override
    public synchronized Connection getConnection() {
        //Check if pool is not created
        if (pool == null) {
            try {
                //Create new pool
                Context initContext = new InitialContext();
                pool = (DataSource) initContext.lookup(CONTEXT);
            } catch (NamingException ex) {
                Logger.getLogger(MySqlDaoFactory.class.getName()).log(Level.WARNING, null, ex);
            }
        }
        try {
            //Return connection 
            return pool.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlDaoFactory.class.getName()).log(Level.WARNING, null, ex);
        }
        return null;
    }

    @Override
    public UserAccountsDAO getUserAccountsDAO(Connection c) {
        return new MySqlUserAccountsDaoImpl(c);
    }

    @Override
    public ApplicationsDAO getApplicationsDAO(Connection c) {
        return new MySqlApplicationsDaoImpl(c);
    }

}
