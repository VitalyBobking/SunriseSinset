package edu.sunrise.sunset.database;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Scanner;

public class SQLite {
    private static final Logger log = Logger.getLogger(SQLite.class);
    private static final String NAME_DB = "city.db";
    private  Statement statement;
    private  Connection connection;

    public void showCity() {
        ResultSet rs;
        try {
            rs = statement.executeQuery("SELECT * FROM CITY");
            while (rs.next()) {
                // read the result set
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
            log.error("error : " + e1.getMessage());
        }

        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("error : " + e.getMessage());
        }
    }
    public void createDataBase(Scanner scanner) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.error("error : " + e.getMessage());
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:"+ NAME_DB);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.
            statement.executeUpdate("CREATE TABLE CITY (ID INTEGER PRIMARY KEY, " +
                    "NAME_CITY VARCHAR(30), " +
                    "LONGITUDE DECIMAL(18.0), " +
                    "LATITUDE DECIMAL(18.0))");

            while(true) {
                    System.out.println("Enter id city");
                    String id = scanner.nextLine();

                    System.out.println("Enter name City");
                    String nameCity = scanner.nextLine();

                    System.out.println("Enter longitude");
                    String longitude = scanner.nextLine();

                    System.out.println("Enter latitude");
                    String latitude = scanner.nextLine();

                    System.out.println();
                    System.out.println("Do you wish to continue? (Yes/No) ");
                    String answer = scanner.nextLine();

                    if(!(answer.equalsIgnoreCase("Yes") || (answer.equalsIgnoreCase("No")))){
                        System.out.println("Invalid option.");
                        System.out.println();
                        System.out.println("Enter valid option: (Yes/No)");
                        answer = scanner.next();
                    }
                    if(answer.equalsIgnoreCase("Yes")){
                        String sql = "INSERT INTO CITY VALUES('" + id + "','" + nameCity + "','" + longitude + "','" + latitude + "')";
                        statement.executeUpdate(sql);
                        continue;
                    }
                    if(answer.equalsIgnoreCase("No")){
                        System.out.println("Thanks, that is all. ");
                        String sql = "INSERT INTO CITY VALUES('" + id + "','" + nameCity + "','" + longitude + "','" + latitude + "')";
                        statement.executeUpdate(sql);
                        break;
                    }

            }

        } catch (SQLException e) {
            e.printStackTrace();
            log.error("error : " + e.getMessage());
        }
    }
}
