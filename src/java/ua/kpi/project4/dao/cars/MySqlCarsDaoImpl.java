/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.kpi.project4.dao.cars;

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
import ua.kpi.project4.dao.MySqlUtility;
import ua.kpi.project4.dao.applications.*;
import ua.kpi.project4.model.Applications;
import ua.kpi.project4.model.Cars;

/**
 *
 * @author User
 */
public class MySqlCarsDaoImpl implements CarsDAO {

    public static final String TABLE = "cars";
    public static final String ID = TABLE + ".CarId";
    public static final String REG_NUM = TABLE + ".RegistrationNumber";
    public static final String PLACES_NUM = TABLE + ".PlacesNumber";
    public static final String IS_VALID = TABLE + ".isValid";

    private final Connection connection;

    public MySqlCarsDaoImpl(Connection c) {
        this.connection = c;
    }

    @Override
    public List<Cars> getAllCars() {

        String[] tables = new String[]{TABLE};
        LinkedHashMap<String, String> conditions = new LinkedHashMap<>();
        String[] fields = {ID, REG_NUM, PLACES_NUM, IS_VALID};
        String sql = MySqlUtility.createSelectStatment(tables, conditions, fields);

        try (PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet result = statement.executeQuery()) {
            List<Cars> carsList = new ArrayList<>();
            while (result.next()) {
                Cars application = new Cars(
                        result.getInt(ID),
                        result.getString(REG_NUM),
                        result.getInt(PLACES_NUM),
                        result.getBoolean(IS_VALID)
                );
                Logger.getLogger(MySqlCarsDaoImpl.class.getName()).info((new java.util.Date()).toString() + " " + statement);
                carsList.add(application);
            }
            return carsList;
        } catch (SQLException ex) {
            Logger.getLogger(MySqlCarsDaoImpl.class.getName()).log(Level.ERROR, null, ex);
            throw new IllegalArgumentException(ex);
        } finally {
            closeConnection();
        }
    }

    @Override
    public Cars getCarById(int id) {

        String[] tables = new String[]{TABLE};
        LinkedHashMap<String, String> conditions = new LinkedHashMap<>();
        conditions.put(ID, "?");
        String[] fields = {ID, REG_NUM, PLACES_NUM, IS_VALID};
        String sql = MySqlUtility.createSelectStatment(tables, conditions, fields);

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Cars application = new Cars(
                            result.getInt(ID),
                            result.getString(REG_NUM),
                            result.getInt(PLACES_NUM),
                            result.getBoolean(IS_VALID)
                    );
                    Logger.getLogger(MySqlCarsDaoImpl.class.getName()).info((new java.util.Date()).toString() + " " + statement);

                    return application;
                } else {
                    String msg = "No such employee.";
                    Logger.getLogger(MySqlCarsDaoImpl.class.getName()).log(Level.ERROR, msg);
                    throw new IllegalArgumentException(msg);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySqlCarsDaoImpl.class.getName()).log(Level.ERROR, null, ex);
            throw new IllegalArgumentException(ex);
        } finally {
            closeConnection();
        }
    }

    @Override
    public void updateCar(Cars car) {

        LinkedHashMap<String, String> conditions = new LinkedHashMap<>();
        conditions.put(ID, "?");
        String[] fields = {IS_VALID};
        String query = MySqlUtility.createUpdateStatment(TABLE, conditions, fields);

        try (PreparedStatement statment = connection.prepareStatement(query)) {
            statment.setBoolean(1, car.getIsValid());
            statment.setInt(2, car.getCarId());
            statment.executeUpdate();
            Logger.getLogger(MySqlCarsDaoImpl.class.getName()).info((new Date()).toString() + " " + statment);
        } catch (SQLException ex) {
            Logger.getLogger(MySqlCarsDaoImpl.class.getName()).log(Level.ERROR, null, ex);
        } finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlCarsDaoImpl.class.getName()).log(Level.ERROR, null, ex);
        }
    }
}
