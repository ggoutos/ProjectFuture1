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
        if (this.insuranceEndDate.compareTo(today)<0) { // insuranceEndDate < today
            return true;
        } else {  // insuranceEndDate >= today
            return false;
        }
    }

    public long daysToExpire() {
        LocalDate today = LocalDate.now();
        if (!expiredInsurance()) { // an den exei liksei h asfaleia
            return (this.insuranceEndDate.toEpochDay() - today.toEpochDay());
        } else { // an exei lhksei h asfaleia
            return -1;
        }
    }

}
