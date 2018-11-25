package gr.project.future.enums;

public enum OptionIO {
    NONE (0),
    FILE  (1),
    CONSOLE(2);

    private int option;

    OptionIO(int option) {
        this.option = option;
    }

    public int getOption() {
        return option;
    }

}