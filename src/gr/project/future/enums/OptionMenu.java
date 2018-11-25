package gr.project.future.enums;

public enum OptionMenu {
    NONE(0),
    FILE  (1),
    CONSOLE(2);

    private int option;

    OptionMenu(int option) {
        this.option = option;
    }

    public int getOption() {
        return option;
    }

}