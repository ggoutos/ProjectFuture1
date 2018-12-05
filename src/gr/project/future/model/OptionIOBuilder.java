package gr.project.future.model;

import gr.project.future.enums.OptionIO;

public class OptionIOBuilder {

    public OptionMenu getOption(int option) {

        if (option == OptionIO.FILE.getOption()) {
            return (new VehicleInsuranceStatus());
        } else if (option == OptionIO.CONSOLE.getOption()) {
            return (new VehicleInsuranceStatus());
        }

        return null;
    }
}
