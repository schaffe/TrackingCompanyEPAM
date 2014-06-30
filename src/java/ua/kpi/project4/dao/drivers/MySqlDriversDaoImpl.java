/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.dao.drivers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.kpi.project4.dao.DaoFactory;
import ua.kpi.project4.dao.MySqlUtility;
import ua.kpi.project4.dao.applications.*;
import static ua.kpi.project4.dao.applications.MySqlApplicationsDaoImpl.DRIVER_ID;
import ua.kpi.project4.dao.cars.*;
import ua.kpi.project4.model.Applications;
import ua.kpi.project4.model.Cars;
import ua.kpi.project4.model.Drivers;
import ua.kpi.project4.model.UserAccounts;

/**
 *
 * @author User
 */
public class MySqlDriversDaoImpl implements DriversDao {

    public static final String TABLE = "drivers";
    public static final String ID = TABLE + ".DriverId";
    public static final String USER_ID = TABLE + ".UserAccountId";
    public static final String CAR_ID = TABLE + ".CarId";

    private final Connection connection;

    public MySqlDriversDaoImpl(Connection c) {
        this.connection = c;
    }

    @Override
    public List<Drivers> getAll() {

        String[] tables = new String[]{TABLE};
        LinkedHashMap<String, String> conditions = new LinkedHashMap<>();
        String[] fields = {ID, USER_ID, CAR_ID};
        String sql = MySqlUtility.createSelectStatment(tables, conditions, fields);

        try (PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery()) {
            List<Drivers> driverList = new ArrayList<>();
            while (result.next()) {
                DaoFactory daoFactory = DaoFactory.getDaoFactory();
                Cars car;
                if (result.getInt(CAR_ID) != 0) {
                    car = daoFactory.getCarsDao(daoFactory.getConnection()).getById(result.getInt(CAR_ID));
                } else {
                    car = null;
                }
                UserAccounts account = daoFactory.getUserAccountsDAO(daoFactory.getConnection()).getById(result.getInt(USER_ID));

                Drivers application = new Drivers(
                        result.getInt(ID),
                        account,
                        car
                );
                Logger.getLogger(MySqlDriversDaoImpl.class.getName()).info((new java.util.Date()).toString() + " " + statement);
                driverList.add(application);
            }
            return driverList;
        } catch (SQLException ex) {
            Logger.getLogger(MySqlDriversDaoImpl.class.getName()).log(Level.ERROR, null, ex);
            throw new IllegalArgumentException(ex);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Drivers getById(int id) {

        String[] tables = new String[]{TABLE};
        LinkedHashMap<String, String> conditions = new LinkedHashMap<>();
        conditions.put(ID, "?");
        String[] fields = {ID, USER_ID, CAR_ID};
        String sql = MySqlUtility.createSelectStatment(tables, conditions, fields);

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    DaoFactory daoFactory = DaoFactory.getDaoFactory();
                    Cars car = daoFactory.getCarsDao(daoFactory.getConnection()).getById(result.getInt(CAR_ID));
                    UserAccounts account = daoFactory.getUserAccountsDAO(daoFactory.getConnection()).getById(result.getInt(USER_ID));

                    Drivers application = new Drivers(
                            result.getInt(ID),
                            account,
                            car
                    );
                    Logger.getLogger(MySqlDriversDaoImpl.class.getName()).info((new java.util.Date()).toString() + " " + statement);

                    return application;
                } else {
                    String msg = "No such employee.";
                    Logger.getLogger(MySqlDriversDaoImpl.class.getName()).log(Level.ERROR, msg);
                    throw new IllegalArgumentException(msg);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySqlDriversDaoImpl.class.getName()).log(Level.ERROR, null, ex);
            throw new IllegalArgumentException(ex);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void update(Drivers driver) {

        LinkedHashMap<String, String> conditions = new LinkedHashMap<>();
        conditions.put(ID, "?");
        String[] fields = {CAR_ID};
        String query = MySqlUtility.createUpdateStatment(TABLE, conditions, fields);

        try (PreparedStatement statment = connection.prepareStatement(query)) {
            statment.setInt(1, driver.getCar().getCarId());
            statment.setInt(2, driver.getDriverId());
            statment.executeUpdate();
            Logger.getLogger(MySqlDriversDaoImpl.class.getName()).info((new Date()).toString() + " " + statment);
        } catch (SQLException ex) {
            Logger.getLogger(MySqlDriversDaoImpl.class.getName()).log(Level.ERROR, null, ex);
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlDriversDaoImpl.class.getName()).log(Level.ERROR, null, ex);
        }
    }
}
