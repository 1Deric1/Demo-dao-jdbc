package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection con = null;

    public static Connection getConnection() {
        try {
            if (con == null) {
                Properties prop = loadPropierts();
                String url = prop.getProperty("dburl");
                con = DriverManager.getConnection(url, prop);
            }
        }catch(SQLException e) {
            throw new DbException(e.getMessage());
        }
        return con;
    }
    public static Properties loadPropierts(){
        try(FileInputStream fis = new FileInputStream("db.properties")) {
            Properties prop = new Properties();
            prop.load(fis);
            return prop;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }
    public static void closeConnection() {
        try{
            if(con != null) {
                con.close();
            }
        }catch(SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
    public static void closeStatement(Statement st) {
        try {
            if (st != null) {
                st.close();
            }
        }
        catch(SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        }
        catch(SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
}
