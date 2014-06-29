/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.dao.applications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.kpi.project4.dao.MySqlUtility;
import ua.kpi.project4.model.Applications;

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

    private final Connection connection;

    public MySqlApplicationsDaoImpl(Connection c) {
        this.connection = c;
    }

    @Override
    public List<Applications> getAllApplications() {

        String[] tables = new String[]{TABLE};
        LinkedHashMap<String, String> conditions = new LinkedHashMap<>();
        String[] fields = {ID, CREATOR_ID, FROM, DESTINATION, ARRIVAL_TIME, PASSENGERS_NUM, STATUS};
        String sql = MySqlUtility.createSelectStatment(tables, conditions, fields);

        try (PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery()) {
            List<Applications> applicationList = new ArrayList<>();
            while (result.next()) {
                Applications application = new Applications(
                        result.getInt(ID),
                        result.getInt(CREATOR_ID),
                        result.getString(FROM),
                        result.getString(DESTINATION),
                        result.getDate(ARRIVAL_TIME),
                        result.getInt(PASSENGERS_NUM),
                        result.getString(STATUS)
                );
                Logger.getLogger(MySqlApplicationsDaoImpl.class.getName()).info((new java.util.Date()).toString() + " " + statement);
                applicationList.add(application);
            }
            return applicationList;
        } catch (SQLException ex) {
            Logger.getLogger(MySqlApplicationsDaoImpl.class.getName()).log(Level.ERROR, null, ex);
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public Applications getApplicationsById(int id) {

        String[] tables = new String[]{TABLE};
        LinkedHashMap<String, String> conditions = new LinkedHashMap<>();
        conditions.put(ID, "?");
        String[] fields = {ID, CREATOR_ID, FROM, DESTINATION, ARRIVAL_TIME, PASSENGERS_NUM, STATUS};
        String sql = MySqlUtility.createSelectStatment(tables, conditions, fields);

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Applications application = new Applications(
                            result.getInt(ID),
                            result.getInt(CREATOR_ID),
                            result.getString(FROM),
                            result.getString(DESTINATION),
                            result.getDate(ARRIVAL_TIME),
                            result.getInt(PASSENGERS_NUM),
                            result.getString(STATUS)
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
        }
    }

    @Override
    public int insertApplications(Applications applications) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateApplications(Applications applications) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteApplications(Applications applications) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
