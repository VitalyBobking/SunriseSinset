package edu.sunrise.sunset;


import edu.sunrise.sunset.database.SQLite;
import edu.sunrise.sunset.network.RetrofitRequestController;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SQLite sqLite = new SQLite();
        RetrofitRequestController controller = new RetrofitRequestController();

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println(" ");
            System.out.println("1\t Enter city or location");
            System.out.println("2\t Show available cities");
            System.out.println("3\t make a request");
            System.out.println("4\t close application");
            System.out.println("Please enter your choice:");
            System.out.println(" ");

            int choice = sc.nextInt();
            if(choice == 1) {
                sqLite.enterCity(fillOutCityData(scanner));
            } else if(choice == 2) {
                sqLite.showCities();
            } else if(choice == 3) {
                sqLite.showNameCitiesAndId();
                System.out.println("Enter ID City");
                int idCity = sc.nextInt();
                controller.requestData(sqLite.getLatLngByIdCity(idCity));
            } else if(choice == 4) {
                sqLite.closeDatabase();
                System.out.println("Application is closed");
                break;
            }
        }
    }

    private static String fillOutCityData(Scanner scanner) {

        String nameCity;
        do {
            System.out.println("Enter name City");
            nameCity = scanner.nextLine();
        } while (!verifyNameCity(nameCity));

        String latitude;
        do {
            System.out.println("Enter latitude");
            latitude = scanner.nextLine();
        } while (!validateData(latitude));

        String longitude;
        do {
            System.out.println("Enter longitude");
            longitude  = scanner.nextLine();
        } while (!validateData(longitude));

        System.out.println();

        return "INSERT INTO CITY (NAME_CITY, LATITUDE, LONGITUDE) VALUES('" + nameCity + "','"
                                                                            + latitude + "','"
                                                                            + longitude + "')";
    }

    private static boolean validateData(String data) {
        try {
            Double.parseDouble(data);
        } catch (Exception e) {
            System.out.println("Incorrect value");
            return false;
        }
        return true;
    }

    private static boolean verifyNameCity(String name) {
            name = name.trim();
            boolean matches = name.matches("[a-zA-Z]*");
            if (name.equals("")) {
                System.out.println("Incorrect value");
                return false;
            }
            if(!matches) {
                System.out.println("Incorrect value");
            }
        return matches;
    }
}






