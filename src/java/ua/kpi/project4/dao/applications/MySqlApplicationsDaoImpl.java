/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.dao.applications;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.kpi.project4.dao.DaoFactory;
import ua.kpi.project4.dao.MySqlUtility;
import ua.kpi.project4.model.Applications;
import ua.kpi.project4.model.Drivers;
import ua.kpi.project4.model.UserAccounts;

/**
 *
 * @author User
 */
public class MySqlApplicationsDaoImpl implements ApplicationsDAO {

    public static final String TABLE = "applications";
    public static final String ID = TABLE + ".ApplicationId";
    public static final String CREATOR_ID = TABLE + ".CreatorUserAccountId";
    public static final String FROM = TABLE + ".From";
    public static final String DESTINATION = TABLE + ".Destination";
    public static final String ARRIVAL_TIME = TABLE + ".ArrivalTime";
    public static final String PASSENGERS_NUM = TABLE + ".PassengersNum";
    public static final String STATUS = TABLE + ".Status";
    public static final String DRIVER_ID = TABLE + ".DriverId";
    public static final String DATE_CREATE = TABLE + ".DateCreate";

    private final Connection connection;

    public MySqlApplicationsDaoImpl(Connection c) {
        this.connection = c;
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlApplicationsDaoImpl.class.getName()).log(Level.ERROR, null, ex);
        }
    }

    @Override
    public List<Applications> getAllApplications() {

        String[] tables = new String[]{TABLE};
        LinkedHashMap<String, String> conditions = new LinkedHashMap<>();
        String[] fields = {ID, CREATOR_ID, FROM, DESTINATION, ARRIVAL_TIME, PASSENGERS_NUM, STATUS, DRIVER_ID, DATE_CREATE};
        String sql = MySqlUtility.createSelectStatment(tables, conditions, fields);

        try (PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery()) {
            List<Applications> applicationList = new ArrayList<>();
            while (result.next()) {
                Applications application = new Applications(
                        result.getInt(ID),
                        new UserAccounts(result.getInt(CREATOR_ID)),
                        result.getString(FROM),
                        result.getString(DESTINATION),
                        result.getDate(ARRIVAL_TIME),
                        result.getInt(PASSENGERS_NUM),
                        result.getString(STATUS),
                        new Drivers(result.getInt(DRIVER_ID)),
                        result.getDate(DATE_CREATE)
                );
                Logger.getLogger(MySqlApplicationsDaoImpl.class.getName()).info((new java.util.Date()).toString() + " " + statement);
                applicationList.add(application);
            }
            return applicationList;
        } catch (SQLException ex) {
            Logger.getLogger(MySqlApplicationsDaoImpl.class.getName()).log(Level.ERROR, null, ex);
            throw new IllegalArgumentException(ex);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Applications getApplicationsById(int id) {

        String[] tables = new String[]{TABLE};
        LinkedHashMap<String, String> conditions = new LinkedHashMap<>();
        conditions.put(ID, "?");
        String[] fields = {ID, CREATOR_ID, FROM, DESTINATION, ARRIVAL_TIME, PASSENGERS_NUM, STATUS, DRIVER_ID, DATE_CREATE};
        String sql = MySqlUtility.createSelectStatment(tables, conditions, fields);

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    DaoFactory daoFactory = DaoFactory.getDaoFactory();
                    Drivers driver = daoFactory.getDriversDao(daoFactory.getConnection()).getById(result.getInt(DRIVER_ID));
                    UserAccounts account = daoFactory.getUserAccountsDAO(daoFactory.getConnection()).getById(result.getInt(CREATOR_ID));
                    
                    Applications application = new Applications(
                            result.getInt(ID),
                            account,
                            result.getString(FROM),
                            result.getString(DESTINATION),
                            result.getDate(ARRIVAL_TIME),
                            result.getInt(PASSENGERS_NUM),
                            result.getString(STATUS),
                            driver,
                            result.getDate(DATE_CREATE)
                    );
                    Logger.getLogger(MySqlApplicationsDaoImpl.class.getName()).info((new java.util.Date()).toString() + " " + statement);

                    return application;
                } else {
                    String msg = "No such employee.";
                    Logger.getLogger(MySqlApplicationsDaoImpl.class.getName()).log(Level.ERROR, msg);
                    throw new IllegalArgumentException(msg);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySqlApplicationsDaoImpl.class.getName()).log(Level.ERROR, null, ex);
            throw new IllegalArgumentException(ex);
        } finally {
            closeConnection();
        }
    }

    @Override
    public int insertApplications(Applications applications) {
        int insertedKey = 0;
        String query = MySqlUtility.createInsertStatment(TABLE, ID, CREATOR_ID, FROM, DESTINATION, ARRIVAL_TIME, PASSENGERS_NUM);

        try (PreparedStatement statment = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);) {
            statment.setInt(1, applications.getApplicationId());
            statment.setInt(2, applications.getCreatorUserAccount().getUserAccountId());
            statment.setString(3, applications.getFrom());
            statment.setString(4, applications.getDestination());
            statment.setDate(5, new Date(applications.getArrivalTime().getTime()));
            statment.setInt(6, applications.getPassengersNum());

            //Get orderId
            int result = statment.executeUpdate();
            if (result > 0) {
                insertedKey = MySqlUtility.getKey(statment);
                applications.setApplicationId(result);
            }
            Logger.getLogger(MySqlApplicationsDaoImpl.class.getName()).info((new java.util.Date()).toString() + " " + statment);
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlApplicationsDaoImpl.class.getName()).log(Level.ERROR, null, ex);
        }
        return insertedKey;
    }

    @Override
    public void updateApplications(Applications application) {
        LinkedHashMap<String, String> conditions = new LinkedHashMap<>();
        conditions.put(ID, "?");
        String[] fields = {STATUS, DRIVER_ID};
        String query = MySqlUtility.createUpdateStatment(TABLE, conditions, fields);

        try (PreparedStatement statment = connection.prepareStatement(query)) {
            statment.setString(1, application.getStatus());
            statment.setInt(2, application.getDriver().getDriverId());
            statment.setInt(3, application.getApplicationId());
            statment.executeUpdate();
            Logger.getLogger(MySqlApplicationsDaoImpl.class.getName()).info((new java.util.Date()).toString() + " " + statment);
        } catch (SQLException ex) {
            Logger.getLogger(MySqlApplicationsDaoImpl.class.getName()).log(Level.ERROR, null, ex);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void deleteApplications(Applications applications) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
