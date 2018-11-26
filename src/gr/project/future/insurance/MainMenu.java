package gr.project.future.insurance;

import gr.project.future.enums.OptionIO;
import gr.project.future.enums.OptionMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static gr.project.future.insurance.Vehicle.getForecomingExpiries;
import static gr.project.future.insurance.Vehicle.getInsuranceStatusBasedOnPlate;
import static gr.project.future.utils.Utilities.loadData;

public class MainMenu {

    private int optionMenu = -1;
    private int optionIO = -1;

    public MainMenu() {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select Functionality to perform:");
        System.out.println("*1 Vehicle Insurance Status");
        System.out.println("*2 Forecoming Expiries");
        System.out.println("*3 Uninsured vehicles sorted by plate");
        System.out.println("*4 Fine calculation per owner");

        setOptionMenu(-1);

        while (!OptionMenu.contains(getOptionMenu())) {
            try {
                setOptionMenu(Integer.parseInt(br.readLine()));
                if (!OptionMenu.contains(getOptionMenu())) {
                    System.err.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid Format! Try again.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Enter export type:");
        System.out.println("*1 File");
        System.out.println("*2 Console");

        setOptionIO(-1);

        while (!OptionIO.contains(getOptionIO())) {
            try {
                setOptionIO(Integer.parseInt(br.readLine()));
                if (!OptionIO.contains(getOptionIO())) {
                    System.err.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid Format! Try again.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws IOException {

        MainMenu mainMenu = new MainMenu();

        List<Owner> ownersList = new ArrayList<>();
        List<Vehicle> vehiclesList = new ArrayList<>();

        //load and fill lists with data
        loadData(ownersList, vehiclesList, mainMenu.getOptionIO());


        if (mainMenu.getOptionMenu() == OptionMenu.VEHICLE_INSURANCE_STATUS.getOption()) {
            //F1: get Vehicle's insurance based on plate
            System.out.println("Please enter vehicle plate in 'XXX-9999' format:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String plate = br.readLine();
            if (plate.matches("[a-zA-Z]{3}-[0-9]{4}")) {
                getInsuranceStatusBasedOnPlate(vehiclesList, plate, mainMenu.getOptionIO());
            } else {
                System.err.println("Wrong plate format. Try again.");
            }

        }

        if (mainMenu.getOptionMenu() == OptionMenu.FORECOMING_EXPIRIES.getOption()) {
            //F2: get Vehicle's insurance that are about to expire
            System.out.println("To know the vehicles about to expire in the next <X> days, please provide the timeframe (in days): ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int days = Integer.parseInt(br.readLine());

            getForecomingExpiries(vehiclesList, days, mainMenu.getOptionIO());

        }


    }

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
}
