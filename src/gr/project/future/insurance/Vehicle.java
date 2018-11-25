package gr.project.future.insurance;

import java.time.LocalDate;

public class Vehicle {

    // Fields

    private String plate;
    private String ownerName;
    private LocalDate insuranceEndDate;


    // Methods

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public LocalDate getInsuranceEndDate() {
        return insuranceEndDate;
    }

    public void setInsuranceEndDate(LocalDate insuranceEndDate) {
        this.insuranceEndDate = insuranceEndDate;
    }

    public boolean expiredInsurance() {
        LocalDate today = LocalDate.now();
        // insuranceEndDate < today
// insuranceEndDate >= today
        return this.insuranceEndDate.compareTo(today) < 0;
    }

    public long daysToExpire() {
        LocalDate today = LocalDate.now();
        if (!expiredInsurance()) { // an den exei liksei h asfaleia
            return (this.insuranceEndDate.toEpochDay() - today.toEpochDay());
        } else { // an exei lhksei h asfaleia
            return -1;
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

}
