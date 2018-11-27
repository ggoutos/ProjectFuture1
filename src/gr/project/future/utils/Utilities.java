package gr.project.future.utils;

import gr.project.future.enums.OptionIO;
import gr.project.future.insurance.Owner;
import gr.project.future.insurance.Vehicle;
import gr.project.future.jdbc.DBConnection;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private static void readFromFile(List<Owner> ownersList, List<Vehicle> vehiclesList) {

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

            if (!ownersList.contains(owner)) {
                ownersList.add(owner);
            }

            if (!vehiclesList.contains(vehicle)) {
                vehiclesList.add(vehicle);
            }
        }
        scanner.close();

    }

    private static void readFromDatabase(List<Owner> ownersList, List<Vehicle> vehiclesList) {

        String QUERY =  "select owner.Name, owner.Surname, vehicle.Plate, vehicle.ExpireDate " +
                        "from vehicle " +
                        "join owner " +
                        "on vehicle.OwnerID =  owner.ID";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(QUERY);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                String plate = rs.getString("Plate");
                String expireDate = rs.getString("ExpireDate");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate insuranceEndDate = LocalDate.parse(expireDate, formatter);

                Owner owner = new Owner(name, surname);
                Vehicle vehicle = new Vehicle(plate, owner, insuranceEndDate);

                if (!ownersList.contains(owner)) {
                    ownersList.add(owner);
                }

                if (!vehiclesList.contains(vehicle)) {
                    vehiclesList.add(vehicle);
                }

            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
