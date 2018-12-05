package gr.project.future.model;

import gr.project.future.insurance.InputValidatorStrategy;

public class OptionMenu implements Option {

    public void showOptions() {
        System.out.println("Select Functionality to perform:");
        System.out.println("*1 Vehicle Insurance Status");
        System.out.println("*2 Forecoming Expiries");
        System.out.println("*3 Uninsured vehicles sorted by plate");
        System.out.println("*4 Fine calculation per owner");
    }


    public int validateUsersInput() {
        return InputValidatorStrategy.validate(gr.project.future.enums.OptionMenu.NONE);
    }

    public void execute() {
        System.out.println("Invalid execution parameters.");
    }

}
