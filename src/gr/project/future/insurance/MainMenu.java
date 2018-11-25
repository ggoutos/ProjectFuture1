package gr.project.future.insurance;

import gr.project.future.enums.OptionIO;
import gr.project.future.enums.OptionMenu;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static gr.project.future.insurance.Vehicle.GetInsuranceStatusBasedOnPlate;

public class MainMenu {

    private int optionMenu;
    private int optionIO;

    public int getOptionIO() {
        return optionIO;
    }

    public void setOptionIO(int optionIO) {
        this.optionIO = optionIO;
    }

    public int getOptionMenu() {
        return optionMenu;
    }

    public void setOptionMenu(int optionMenu) {
        this.optionMenu = optionMenu;
    }

    public MainMenu() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select Functionality to perform:");
        System.out.println("*1 Vehicle Insurance Status");
        System.out.println("*2 Forecoming Expiries");
        System.out.println("*3 Expiries by plate");

        setOptionMenu(OptionMenu.NONE.getOption());

        while (getOptionMenu() == 0) {
            try {
                setOptionMenu(Integer.parseInt(br.readLine()));
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid Format! Try again.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Enter export type:");
        System.out.println("*1 File");
        System.out.println("*2 Console");

        setOptionIO(OptionIO.NONE.getOption());

        try {
            setOptionIO(Integer.parseInt(br.readLine()));
        } catch (NumberFormatException nfe) {
            System.err.println("Invalid Format! Try again.");
        } catch (Exception e) {
            e.printStackTrace();
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

            if (!ownersList.contains(owner)) {
                ownersList.add(owner);
            }

            if (!vehiclesList.contains(vehicle)) {
                vehiclesList.add(vehicle);
            }
        }
        scanner.close();

    }

    public static void main(String[] args) throws IOException {

        MainMenu mainMenu = new MainMenu();

        List<Owner> ownersList = new ArrayList<>();
        List<Vehicle> vehiclesList = new ArrayList<>();

        //read from csv and fill lists with data
        readFromFile(ownersList, vehiclesList);

        //F1: get Vehicle's insurance based on plate
        System.out.println("Please enter vehicle plate:");
        //TODO plate format error
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        GetInsuranceStatusBasedOnPlate(vehiclesList, br.readLine(), mainMenu.getOptionIO());


    }
}
