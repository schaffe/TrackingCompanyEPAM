package ua.kpi.project4.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Helper class for MySQL DAO issues
 */
public final class MySqlUtility {

    /**
     * Returns primary key of row wich was inserted
     *
     * @param statment sql statment
     * @return PK
     * @throws SQLException
     */
    public static int getKey(PreparedStatement statment) throws SQLException {
        int insertedKey;
        try (ResultSet keys = statment.getGeneratedKeys()) {
            keys.next();
            insertedKey = keys.getInt(1);
        }
        return insertedKey;
    }

    /**
     * Convertation Date to Timestamp
     *
     * @param date Date object
     * @return Timestamp object
     */
    public static Timestamp dateToTimestamp(Date date) {
        if (date != null) {
            long time = date.getTime();
            return new Timestamp(time);
        }
        return null;
    }

    /**
     * Create SELECT query string in depending on tables, conditions and columns
     * list
     *
     * @param tables tables in which gets data
     * @param conditions conditions for selecting rows
     * @param fields columns for selecting
     * @return string presentation of query
     */
    public static String createSelectStatment(String[] tables, LinkedHashMap<String, String> conditions, String... fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        //adding names of columns 
        if (fields.length != 0) {
            for (int i = 0; i < fields.length; i++) {
                sb.append(fields[i]);
                if (i < fields.length - 1) {
                    sb.append(',');
                }
            }
        } else {
            sb.append(" *");
        }

        sb.append(" FROM ");

        //adding names of tables
        if (tables.length != 0) {
            for (int i = 0; i < tables.length; i++) {
                sb.append(tables[i]);
                if (i < tables.length - 1) {
                    sb.append(" JOIN ");
                }
            }
        }
        //adding conditions
        if (conditions != null) {
            if (!conditions.isEmpty()) {
                if (tables.length > 1) {
                    sb.append(" ON ");
                    int i = 0;
                    for (String key : conditions.keySet()) {
                        sb.append(key);
                        //set separator betwen left and right side of condition
                        sb.append(getLeftExpressionSeparetor(conditions.get(key)));
                        sb.append(conditions.get(key));
                        if (i < conditions.size() - 1) {
                            sb.append(" AND ");
                        }
                        i++;
                    }
                } else {
                    sb.append(" WHERE ");
                    int i = 0;
                    for (String key : conditions.keySet()) {
                        //set left side of condition
                        sb.append(key);
                        //set separator betwen left and right side of condition
                        sb.append(getLeftExpressionSeparetor(conditions.get(key)));
                        //set right side of condition
                        sb.append(conditions.get(key));
                        if (i < conditions.size() - 1) {
                            sb.append(" AND ");
                        }
                        i++;
                    }
                }
            }
        }

        return sb.toString();
    }

    /**
     * Determine separator betwen left and right side of condition
     *
     * @param value right side of condition
     * @return separator
     */
    static String getLeftExpressionSeparetor(String value) {
        if (value.equalsIgnoreCase("NULL") || value.equalsIgnoreCase("NOT NULL")) {
            return " IS ";
        }
        return "=";
    }

    /**
     * Create INSERT query string
     *
     * @param table in which table insert
     * @param fields which fields will insert
     * @return string presentation of query
     */
    public static String createInsertStatment(String table, String... fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(table);
        sb.append('(');
        int i = 0;
        //adding names of columns 
        if (fields.length != 0) {
            for (String field : fields) {
                sb.append(field);
                if (i < fields.length - 1) {
                    sb.append(',');
                }
                i++;
            }
        }
        sb.append(") ");
        //adding special symbol ? in count of columns
        if (fields.length != 0) {
            sb.append("VALUES(");
            for (String f : fields) {
                sb.append("?,");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(')');
        }
        return sb.toString();
    }

    /**
     * Create UPDATE query string
     *
     * @param table in which table update data
     * @param conditions conditions for selecting rows
     * @param fields columns for updating
     * @return string presentation of query
     */
    public static String createUpdateStatment(String table, LinkedHashMap<String, String> conditions, String... fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(table);
        sb.append(" SET ");
        int i = 0;
        //adding names of columns 
        if (fields.length != 0) {
            for (String field : fields) {
                sb.append(field);
                if (i < fields.length - 1) {
                    sb.append(" = ?,");
                } else {
                    sb.append(" = ?");
                }
                i++;
            }
        }
        //adding conditions
        if (conditions != null) {
            if (!conditions.isEmpty()) {
                sb.append(" WHERE ");
                int j = 0;
                for (String key : conditions.keySet()) {
                    //set left side of condition
                    sb.append(key);
                    //set separator betwen left and right side of condition
                    sb.append(getLeftExpressionSeparetor(conditions.get(key)));
                    //set right side of condition
                    sb.append(conditions.get(key));
                    if (j < conditions.size() - 1) {
                        sb.append(" AND ");
                    }
                    j++;
                }
            }
        }
        return sb.toString();
    }

}
