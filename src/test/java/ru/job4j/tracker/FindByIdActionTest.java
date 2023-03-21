package ru.job4j.tracker;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FindByIdActionTest {
    @Test
    public void whenItemFoundById() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item(1, "Item");
        tracker.add(item);
        FindByIdAction findAction = new FindByIdAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        findAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "=== Find Item by id ===" + ln + item + ln
        );
    }

    @Test
    public void whenItemNotFound() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = new Item(1, "Item");
        tracker.add(item);
        FindByIdAction findAction = new FindByIdAction(out);
        int findId = 2;
        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(findId);

        findAction.execute(input, tracker);
        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "=== Find Item by id ==="
                        + ln + "Заявка с введенным id: "
                        + findId + " не найдена." + ln
        );
    }
}