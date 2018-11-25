package gr.project.future.insurance;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    public static void GetInsuranceStatusBasedOnPlate(List<Vehicle> vehicleList, String plate, int optionIO) {

        for (Vehicle v: vehicleList) {
            if (v.getPlate().equals(plate)) {
                String sout = "The vehicle with plate '" + plate + "' has ";
                if (!v.insuranceIsExpired()) {
                    sout = sout + "not ";
                }
                sout = sout + "expired.";
                if (optionIO == OptionIO.FILE.getOption()){
                    // TODO File write
                }
                else if (optionIO == OptionIO.CONSOLE.getOption()) {
                    System.out.println(sout);
                }
                else {
                    System.out.println(sout);
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select Functionality to perform:");
        System.out.println("*1 Vehicle Insurance Status");
        System.out.println("*2 Forecoming Expiries");
        System.out.println("*3 Expiries by plate");

        int optionMenu = 0;

        while (optionMenu == 0) {
            try {
                optionMenu = Integer.parseInt(br.readLine());
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid Format! Try again.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Enter export type:");
        System.out.println("*1 File");
        System.out.println("*2 Console");

        int optionIO = 0;

        try {
            optionIO = Integer.parseInt(br.readLine());
        } catch (NumberFormatException nfe) {
            System.err.println("Invalid Format! Try again.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("resources/plates.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.useDelimiter(",");

        List<Owner> ownersList = new ArrayList<>();
        List<Vehicle> vehiclesList = new ArrayList<>();

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

        //get Vehicle insurance based on plate
        System.out.println("Please enter vehicle plate:");
        //TODO plate format error
        GetInsuranceStatusBasedOnPlate(vehiclesList, br.readLine(), optionIO);


    }
}
