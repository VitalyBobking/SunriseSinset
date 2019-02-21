package edu.sunrise.sunset.database;

import edu.sunrise.sunset.model.CityLatLng;
import org.apache.log4j.Logger;

import java.sql.*;

public class SQLite {

    private static final Logger log = Logger.getLogger(SQLite.class);
    public static final String NAME_DB = "city.db";
    public static final String NAME_TABLE = "CITY";
    private  Statement statement;
    private  Connection connection;

    public SQLite() {
        createDataBase();
    }

    public void showNameCitiesAndId() {
        ResultSet rs;
        try {
            rs = statement.executeQuery("SELECT ID, NAME_CITY FROM CITY");
            while (rs.next()) {
                System.out.println(rs.getString("NAME_CITY")
                        + " : " + "ID = " + rs.getInt("ID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("showNameCitiesAndId" + e.getMessage());
        }
    }

    public double[] getLatLngByIdCity(int id) {
        ResultSet rs;
        CityLatLng city = new CityLatLng();
        try {
            rs = statement.executeQuery("SELECT LATITUDE, LONGITUDE FROM CITY WHERE id = " + id );
            while (rs.next()) {
                city.setLat(rs.getDouble("LATITUDE"));
                city.setLnt(rs.getDouble("LONGITUDE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("getLatLngByIdCity" + e.getMessage());
        }

        return new double[]{city.getLat(), city.getLnt()};
    }

    public void showCities() {
        ResultSet rs;
        try {
            rs = statement.executeQuery("SELECT * FROM CITY");
            while (rs.next()) {
                System.out.println("ID = " + rs.getInt("ID"));
                System.out.println("name = " + rs.getString("NAME_CITY"));
                System.out.println("longitude = " + rs.getDouble("LONGITUDE"));
                System.out.println("latitude = " + rs.getDouble("LATITUDE"));

                log.info("ID = " + rs.getInt("ID"));
                log.info("name = " + rs.getString("NAME_CITY"));
                log.info("longitude = " + rs.getDouble("LONGITUDE"));
                log.info("latitude = " + rs.getDouble("LATITUDE"));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
            log.error("showCities error : " + e1.getMessage());
        }
    }

    public void enterCity(String request) {
        try {
            statement.executeUpdate(request);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("enterCity error : " + e.getMessage());
        }
    }

    private void createDataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error("createDataBase error : " + e.getMessage());
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+ NAME_DB);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS "+NAME_TABLE+" (" +
                                                 "id INTEGER PRIMARY KEY, " +
                                                 "NAME_CITY VARCHAR(30), " +
                                                 "LATITUDE DECIMAL(18.0), " +
                                                 "LONGITUDE DECIMAL(18.0))");

        } catch (SQLException e) {
            e.printStackTrace();
            log.error("createDataBase error : " + e.getMessage());
        }
    }
    public void closeDatabase() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("getLatLngByIdCity error : " + e.getMessage());
        }
    }
}
