package gr.project.future.utils;

import gr.project.future.enums.OptionIO;
import gr.project.future.insurance.Owner;
import gr.project.future.insurance.Vehicle;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Utilities {
    public static void loadData(List<Owner> ownersList, List<Vehicle> vehiclesList, int optionIO) {

        if (optionIO == OptionIO.FILE.getOption()) {
            //read from csv and fill lists with data
            readFromFile(ownersList, vehiclesList);
        }
        else if (optionIO == OptionIO.CONSOLE.getOption()) {
            //read from db and fill lists with data
            readFromDatabase(ownersList, vehiclesList);
        }
        else {
            System.err.println("Invalid option.");
        }

    }

    public static void readFromFile(List<Owner> ownersList, List<Vehicle> vehiclesList) {

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("resources/plates.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.useDelimiter(",");

        while(scanner.hasNext()){
            String ownerName = scanner.next().strip();
            String ownerSurname = scanner.next().strip();
            String plate = scanner.next().strip();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate insuranceEndDate = LocalDate.parse(scanner.next().strip(), formatter);

            Owner owner = new Owner(ownerName, ownerSurname);
            Vehicle vehicle = new Vehicle(plate, owner, insuranceEndDate);
            //FIXME check contains
            if (!ownersList.contains(owner)) {
                ownersList.add(owner);
            }

            if (!vehiclesList.contains(vehicle)) {
                vehiclesList.add(vehicle);
            }
        }
        scanner.close();

    }

    public static void readFromDatabase(List<Owner> ownersList, List<Vehicle> vehiclesList) {
        //TODO readFromDatabase implementation
        System.out.println("Read from database not ready yet.");
    }
}
