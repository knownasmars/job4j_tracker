package ru.job4j.tracker;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByNameActionTest {
    @Test
    public void whenItemFoundByName() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item(1, "Item");
        tracker.add(item);
        FindByNameAction findAction = new FindByNameAction(out);

        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn("Item");

        findAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "=== Find Item by name ===" + ln + item + ln
        );
    }

    @Test
    public void whenItemNotFound() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item(1, "Item");
        tracker.add(item);
        FindByNameAction findAction = new FindByNameAction(out);
        String name = "Item2";
        Input input = mock(Input.class);

        when(input.askStr(any(String.class))).thenReturn(name);

        findAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "=== Find Item by name ==="
                        + ln + "Заявки с именем: "
                        + name + " не найдены." + ln
        );
    }
}