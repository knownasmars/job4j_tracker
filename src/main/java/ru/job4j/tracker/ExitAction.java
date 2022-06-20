package ru.job4j.tracker;

public class ExitAction implements UserAction {
    private final Output out;

    public ExitAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Exit the program";
    }

    @Override
    public boolean execute(Input input, Tracker tracker) {
        out.println("=== Exit the program ===");
        return false;
    }
}
