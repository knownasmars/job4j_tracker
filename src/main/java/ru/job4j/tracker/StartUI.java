package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public void init(Input input, Store memTracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Wrong input, you can select: 0 .. " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, memTracker);
        }
    }

    private void showMenu(List<UserAction> actions) {
        out.println("Menu:");
        for (int index = 0; index < actions.size(); index++) {
            out.println(index + ". " + actions.get(index).name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ValidateInput(output, new ConsoleInput());
        try (Store memTracker = new SqlTracker()) {
        List<UserAction> actions = new ArrayList<>(
                List.of(new CreateAction(output),
                        new CreateManyItems(output),
                        new ShowAction(output),
                        new EditAction(output),
                        new DeleteAction(output),
                        new DeleteAllItems(output),
                        new FindByNameAction(output),
                        new FindByIdAction(output),
                        new ExitAction(output)
                )
        );
        new StartUI(output).init(input, memTracker, actions);
    } catch (Exception e) {
        e.printStackTrace();
        }
    }
}