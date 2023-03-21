package ru.job4j.tracker;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteActionTest {

    @Test
    public void whenDeleteFoundfItemThenSuccess() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        tracker.add(new Item("Item #1"));
        DeleteAction deleteAction = new DeleteAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        deleteAction.execute(input, tracker);

        String ln = System.lineSeparator();
        assertThat(out.toString()).isEqualTo(
                "=== Delete Item ===" + ln + "Заявка удалена успешно." + ln);
        assertThat(tracker.findAll()).hasSize(0);
    }

    @Test
    public void whenItemNotFoundThenDeletionIsUnsuccessful() {
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        DeleteAction deleteAction = new DeleteAction(out);

        Input input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);

        String ln = System.lineSeparator();
        deleteAction.execute(input, tracker);
        assertThat(out.toString()).isEqualTo(
                "=== Delete Item ===" + ln + "Ошибка удаления заявки." + ln);
    }
}