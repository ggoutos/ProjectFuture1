package gr.project.future.insurance;

import gr.project.future.model.Option;
import gr.project.future.model.OptionMenu;
import gr.project.future.model.OptionMenuBuilder;

public class InsuranceApp {
    public static void main(String[] args) {

        Option optionMenu = new OptionMenu();

        optionMenu.showOptions();
        int option = optionMenu.validateUsersInput();
        optionMenu = (new OptionMenuBuilder()).getOption(option);
        optionMenu.execute();

    }
}
