package gr.project.future.insurance;

public enum OptionIO {
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