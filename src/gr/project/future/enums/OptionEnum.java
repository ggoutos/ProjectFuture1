package gr.project.future.enums;

public interface OptionEnum {

    default boolean contains(int value) {
        for (OptionIO o : OptionIO.values()) {
            if (value == o.getOption()) {
                return true;
            }
        }
        return false;
    }
}
