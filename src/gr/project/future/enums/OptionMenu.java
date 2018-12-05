package gr.project.future.enums;

public enum OptionMenu implements OptionEnum{
    NONE(0),
    VEHICLE_INSURANCE_STATUS  (1),
    FORECOMING_EXPIRIES(2),
    UNINSURED_VEHICLES_SORTED_BY_PLATE(3),
    FINE_CALCULATION_PER_OWNER(4);

    private int option;

    OptionMenu(int option) {
        this.option = option;
    }

    public int getOption() {
        return option;
    }

}