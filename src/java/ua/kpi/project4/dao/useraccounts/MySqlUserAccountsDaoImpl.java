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
public class MySqlUserAccountsDaoImpl implements UserAccountsDAO {

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

        String[] tables = new String[]{TABLE};
        LinkedHashMap<String, String> conditions = new LinkedHashMap<>();
        conditions.put(LOGIN, "?");
        String[] fields = {ID, LOGIN, PASSWORD, FULLNAME, PROFILE_ID};
        String sql = MySqlUtility.createSelectStatment(tables, conditions, fields);

        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, login);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    UserAccounts userInfo = new UserAccounts(
                            result.getInt(ID),
                            result.getString(FULLNAME),
                            result.getString(LOGIN),
                            result.getString(PASSWORD),
                            result.getInt(PROFILE_ID)
                    );
                    Logger.getLogger(MySqlUserAccountsDaoImpl.class.getName()).info((new java.util.Date()).toString() + " " + sql);

                    return userInfo;
                } else {
                    String msg = "No such employee.";
                    Logger.getLogger(MySqlUserAccountsDaoImpl.class.getName()).log(Level.ERROR, msg);
                    throw new IllegalArgumentException(msg);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MySqlUserAccountsDaoImpl.class.getName()).log(Level.ERROR, null, ex);
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public void insert(UserAccounts account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(UserAccounts account) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
