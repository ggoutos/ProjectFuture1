package gr.project.future.insurance;

import gr.project.future.enums.OptionIO;

import java.time.LocalDate;
import java.util.List;

public class Vehicle {

    private String plate;
    private Owner owner;
    private LocalDate insuranceEndDate;

    public Vehicle(String plate, Owner owner, LocalDate insuranceEndDate) {
        this.plate = plate;
        this.owner = owner;
        this.insuranceEndDate = insuranceEndDate;
    }

    public static void GetInsuranceStatusBasedOnPlate(List<Vehicle> vehicleList, String plate, int optionIO) {

        boolean found = false;

        for (Vehicle v : vehicleList) {

            if (v.getPlate().equals(plate)) {
                found = true;
                String sout = "The vehicle with plate '" + plate + "' has ";
                if (!v.insuranceIsExpired()) {
                    sout = sout + "not ";
                }
                sout = sout + "expired.";
                if (optionIO == OptionIO.FILE.getOption()) {
                    // TODO File write
                } else if (optionIO == OptionIO.CONSOLE.getOption()) {
                    System.out.println(sout);
                } else {
                    System.out.println(sout);
                }
            }

        }
        if (!found) {
            System.out.println("The vehicle with plate '" + plate + "' was not found.");
        }
    }

    private static String[] bubbleSort(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
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

    public boolean insuranceIsExpired() {
        LocalDate today = LocalDate.now();
        // insuranceEndDate < today
        // insuranceEndDate >= today
        return this.insuranceEndDate.compareTo(today) < 0;
    }

    public long daysToExpire() {
        LocalDate today = LocalDate.now();
        if (!insuranceIsExpired()) { // an den exei liksei h asfaleia
            return (this.insuranceEndDate.toEpochDay() - today.toEpochDay());
        } else { // an exei lhksei h asfaleia
            return -1;
        }
    }

}
