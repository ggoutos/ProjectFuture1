package gr.project.future.insurance;

import gr.project.future.enums.OptionEnum;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class InputValidatorStrategy {

    public static int validate(OptionEnum optionEnum) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int option = -1;

        while (!optionEnum.contains(option)) {
            try {
                option = Integer.parseInt(br.readLine());
                if (!optionEnum.contains(option)) {
                    System.err.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException nfe) {
                System.err.println("Invalid Format! Try again.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return option;
    }
}
