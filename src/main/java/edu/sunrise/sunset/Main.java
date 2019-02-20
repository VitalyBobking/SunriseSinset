package edu.sunrise.sunset;



import edu.sunrise.sunset.database.SQLite;
import edu.sunrise.sunset.json.Controller;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        SQLite sqLite = new SQLite();
        Controller controller = new Controller();
        controller.start();

        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println(" ");
            System.out.println("1\t Create Database");
            System.out.println("2\t Read a data from database");
            System.out.println("3\t make a request");
            System.out.println("4\t close application");
            System.out.println(" ");
            System.out.println("Please enter your choice:");
            System.out.println(" ");

            int choice = sc.nextInt();
            if(choice == 1) {
                sqLite.createDataBase(scanner);
            } else if(choice == 2) {
                sqLite.showCity();
            } else if(choice == 3) {

            } else if(choice == 4) {
                System.out.println("You closed the project");
                break;
            }

        }

    }
}






