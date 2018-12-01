package gr.project.future.insurance;

import gr.project.future.enums.OptionIO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Vehicle {

    private String plate;
    private Owner owner;
    private LocalDate insuranceEndDate;
    private static final String NEW_LINE = "\n";

    public Vehicle(String plate, Owner owner, LocalDate insuranceEndDate) {
        this.plate = plate;
        this.owner = owner;
        this.insuranceEndDate = insuranceEndDate;
    }

    public static void getInsuranceStatusBasedOnPlate(List<Vehicle> vehiclesList, String plate, int optionIO) {

        boolean found = false;
        String message = "";
        String path = "resources/output.txt";

        for (Vehicle v : vehiclesList) {
            if (v.getPlate().equalsIgnoreCase(plate)) {
                found = true;
                message = "The vehicle with plate '" + plate.toUpperCase() + "' has ";
                if (!v.insuranceIsExpired()) {
                    message = message + "not ";
                }
                message = message + "expired.";
            }
        }

        if (!found) {
            message = "The vehicle with plate '" + plate.toUpperCase() + "' was not found.";
        }

        System.out.println(message);

        if (optionIO == OptionIO.FILE.getOption()) {     // OptionIO = FILE
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(path));
                writer.write(message);
                writer.write(NEW_LINE);
                writer.close();
                System.out.println("Message written in log file '" + path + "'");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void getForecomingExpiries(List<Vehicle> vehiclesList, int days, int optionIO) throws IOException {

        List<Vehicle> tempVehiclesList = new ArrayList<>();
        String path = "resources/output.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));

        for (Vehicle v : vehiclesList) {
            if (v.daysToExpire() < 0 || v.daysToExpire() > days) {
                tempVehiclesList.add(v);
            }
        }

        vehiclesList.removeAll(tempVehiclesList);

        for (Vehicle v : vehiclesList) {
            String message = "The insurance of vehicle with plate " + v.getPlate() + ", owner " + v.getOwner().getName()
                    + " " + v.getOwner().getSurname() + " and expiration date " + v.getInsuranceEndDate()
                    + " will expire in " + v.daysToExpire() + " days.";
            System.out.println(message);
            if (optionIO == OptionIO.FILE.getOption()) {
                writer.write(message);
                writer.write(NEW_LINE);
            }
        }

        writer.close();

    }


    public static void getUninsuredVehiclesSortedByPlate(List<Vehicle> vehiclesList, int optionIO) throws IOException {

        List<Vehicle> tempVehiclesList = new ArrayList<>();
        String path = "resources/output.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));

        for (Vehicle v : vehiclesList) {
            if (!v.insuranceIsExpired()) {
                tempVehiclesList.add(v);
            }
        }

        vehiclesList.removeAll(tempVehiclesList);

        Collections.sort(vehiclesList, new VehicleComparator());

        for (Vehicle v : vehiclesList) {
            String message = "The insurance of vehicle with plate " + v.getPlate() + ", owner " + v.getOwner().getName()
                    + " " + v.getOwner().getSurname() + " and expiration date " + v.getInsuranceEndDate()
                    + " has expired " + (-1)*v.daysToExpire() + " days ago.";
            System.out.println(message);
            if (optionIO == OptionIO.FILE.getOption()) {
                writer.write(message);
                writer.write(NEW_LINE);
            }
        }

        writer.close();
    }


    public boolean insuranceIsExpired() {
        LocalDate today = LocalDate.now();
        return this.insuranceEndDate.compareTo(today) < 0;
    }

    public long daysToExpire() {
        LocalDate today = LocalDate.now();
        return this.insuranceEndDate.toEpochDay() - today.toEpochDay();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return plate.equals(vehicle.plate) &&
                owner.equals(vehicle.owner) &&
                insuranceEndDate.equals(vehicle.insuranceEndDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plate, owner, insuranceEndDate);
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public LocalDate getInsuranceEndDate() {
        return insuranceEndDate;
    }

    public void setInsuranceEndDate(LocalDate insuranceEndDate) {
        this.insuranceEndDate = insuranceEndDate;
    }
}
