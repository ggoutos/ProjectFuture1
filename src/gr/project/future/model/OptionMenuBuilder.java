package gr.project.future.model;

public class OptionMenuBuilder {

    public OptionMenu getOption(int option) {

        if (option == gr.project.future.enums.OptionMenu.VEHICLE_INSURANCE_STATUS.getOption()) {
            return (new VehicleInsuranceStatus());
        } else if (option == gr.project.future.enums.OptionMenu.FORECOMING_EXPIRIES.getOption()) {
            return (new VehicleInsuranceStatus());
        } else if (option == gr.project.future.enums.OptionMenu.UNINSURED_VEHICLES_SORTED_BY_PLATE.getOption()) {
            return (new VehicleInsuranceStatus());
        } else if (option == gr.project.future.enums.OptionMenu.FINE_CALCULATION_PER_OWNER.getOption()) {
            return (new VehicleInsuranceStatus());
        }

        return null;
    }
}
