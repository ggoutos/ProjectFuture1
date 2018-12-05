package gr.project.future.model;

import gr.project.future.insurance.InputValidatorStrategy;

public class OptionIO implements Option{


    public  void showOptions() {
        System.out.println("Enter export type:");
        System.out.println("*1 File");
        System.out.println("*2 Console");
    }

    public int validateUsersInput() {
        return InputValidatorStrategy.validate(gr.project.future.enums.OptionIO.NONE);
    }

}
