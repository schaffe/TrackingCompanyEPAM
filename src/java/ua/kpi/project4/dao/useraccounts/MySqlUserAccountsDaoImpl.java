/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ua.kpi.project4.dao.useraccounts;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.kpi.project4.dao.MySqlUtility;
import ua.kpi.project4.model.UserAccounts;

/**
 *
 * @author User
 */
public class MySqlUserAccountsDaoImpl implements UserAccountsDAO{

    public static final String TABLE = "user_accounts";
    public static final String ID = TABLE + ".UserAccountId";
    public static final String LOGIN = TABLE + ".Login";
    public static final String PASSWORD = TABLE + ".Password";
    public static final String FULLNAME = TABLE + ".Fullname";
    public static final String PROFILE_ID = TABLE + ".ProfileId";
    
    private final Connection connection;
   
    public MySqlUserAccountsDaoImpl(Connection c) {
        this.connection = c;
    }
    
    
    @Override
    public UserAccounts getByLogin(String login) {
        UserAccounts userInfo = null;
        try {
            String[] tables = new String[]{TABLE};
            //Set conditions list
            LinkedHashMap<String, String> conditions = new LinkedHashMap<>();
            conditions.put(LOGIN, "?");
            //Set columns list
            String[] fields = {ID, LOGIN, PASSWORD, FULLNAME, PROFILE_ID};
            //Create query string
            String query = MySqlUtility.createSelectStatment(tables, conditions, fields);

            PreparedStatement statment = connection.prepareStatement(query);
            statment.setString(1, login);
            //execute query
            ResultSet result = statment.executeQuery();
            if (result.next()) {
                userInfo = MySqlUtility.getUserInfo(result);
                //In depending of user role - setRole
                userInfo.setRole(((result.getObject(MySqlUserInfoDAO.ROLE) == null)
                        ? null : result.getBoolean(MySqlUserInfoDAO.ROLE)));
            }
            connection.close();
            Logger.getLogger(MySqlUserInfoDAO.class.getName()).info((new java.util.Date()).toString() + " " + query);
        } catch (SQLException ex) {
            Logger.getLogger(MySqlUserInfoDAO.class.getName()).log(Level.ERROR, null, ex);
        }
        return userInfo;    }

    @Override
    public void insert(UserAccounts account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(UserAccounts account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
