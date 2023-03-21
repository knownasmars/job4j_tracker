package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StartUITest {
    @Test
    public void whenCreateItem() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "Item name", "1"}
        );
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>(
                List.of(new CreateAction(out), new ExitAction(out)));
        new StartUI(out).init(in, memTracker, actions);
        assertThat(memTracker.findAll().get(0).getName()).isEqualTo("Item name");
    }

    @Test
    public void whenReplaceItem() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Replaced item"));
        String itemId = String.valueOf(item.getId());
        String replacedName = "New item name";
        Input in = new StubInput(
                new String[] {"0", itemId, replacedName, "1"}
        );
        List<UserAction> actions = new ArrayList<>(
                List.of(new EditAction(out), new ExitAction(out)));
        new StartUI(out).init(in, memTracker, actions);
        assertThat(memTracker.findById(item.getId()).getName()).isEqualTo(replacedName);
    }

    @Test
    public void whenDeleteItem() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("Deleted item"));
        String itemId = String.valueOf(item.getId());
        Input in = new StubInput(
                new String[] {"0", itemId, "1"}
        );
        List<UserAction> actions = new ArrayList<>(
                List.of(new DeleteAction(out), new ExitAction(out)));
        new StartUI(out).init(in, memTracker, actions);
        assertThat(memTracker.findById(item.getId())).isEqualTo(null);
    }

    @Test
    public void whenReplaceItemTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        String replaceName = "New Test Name";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(one.getId()), replaceName, "1"}
        );
        List<UserAction> actions = new ArrayList<>(
                List.of(new EditAction(out), new ExitAction(out)));
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Edit Item" + ln
                        + "1. Exit the program" + ln
                        + "=== Edit Item ===" + ln
                        + "Заявка изменена успешно." + ln
                        + "Menu:" + ln
                        + "0. Edit Item" + ln
                        + "1. Exit the program" + ln
                        + "=== Exit the program ===" + ln
        );
    }

    @Test
    public void whenDeleteItemTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(one.getId()), "1"}
        );
        List<UserAction> actions = new ArrayList<>(
                List.of(new DeleteAction(out),
                new ExitAction(out)));
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Delete Item" + ln
                        + "1. Exit the program" + ln
                        + "=== Delete Item ===" + ln
                        + "Заявка удалена успешно." + ln
                        + "Menu:" + ln
                        + "0. Delete Item" + ln
                        + "1. Exit the program" + ln
                        + "=== Exit the program ===" + ln
        );
    }

    @Test
    public void whenShowActionTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item item = memTracker.add(new Item("test1"));
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        List<UserAction> actions = new ArrayList<>(
                List.of(new ShowAction(out), new ExitAction(out)));
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Show all Items" + ln
                        + "1. Exit the program" + ln
                        + "=== Show all Items ===" + ln
                        + item + ln
                        + "Menu:" + ln
                        + "0. Show all Items" + ln
                        + "1. Exit the program" + ln
                        + "=== Exit the program ===" + ln
        );
    }

    @Test
    public void whenFindByIdActionTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(one.getId()), "1"}
        );
        List<UserAction> actions = new ArrayList<>(
                List.of(new FindByIdAction(out), new ExitAction(out)));
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Find Item by id" + ln
                        + "1. Exit the program" + ln
                        + "=== Find Item by id ===" + ln
                        + one + ln
                        + "Menu:" + ln
                        + "0. Find Item by id" + ln
                        + "1. Exit the program" + ln
                        + "=== Exit the program ===" + ln
        );
    }

    @Test
    public void whenFindByNameActionTestOutputIsSuccessfully() {
        Output out = new StubOutput();
        MemTracker memTracker = new MemTracker();
        Item one = memTracker.add(new Item("test1"));
        Input in = new StubInput(
                new String[] {"0", one.getName(), "1"}
        );
        List<UserAction> actions = new ArrayList<>(
                List.of(new FindByNameAction(out), new ExitAction(out)));
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Find Item by name" + ln
                        + "1. Exit the program" + ln
                        + "=== Find Item by name ===" + ln
                        + one + ln
                        + "Menu:" + ln
                        + "0. Find Item by name" + ln
                        + "1. Exit the program" + ln
                        + "=== Exit the program ===" + ln
        );
    }

    @Test
    public void whenInvalidExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"1", "0"}
        );
        MemTracker memTracker = new MemTracker();
        List<UserAction> actions = new ArrayList<>(
                List.of(new ExitAction(out)));
        new StartUI(out).init(in, memTracker, actions);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "Menu:" + ln
                        + "0. Exit the program" + ln
                        + "Wrong input, you can select: 0 .. 0" + ln
                        + "Menu:" + ln
                        + "0. Exit the program" + ln
                        + "=== Exit the program ===" + ln
        );
    }
}