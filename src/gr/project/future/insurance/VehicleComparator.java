package gr.project.future.insurance;

import java.util.Comparator;

public class VehicleComparator implements Comparator<Vehicle> {

    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return o1.getPlate().compareTo(o2.getPlate());
    }
}
