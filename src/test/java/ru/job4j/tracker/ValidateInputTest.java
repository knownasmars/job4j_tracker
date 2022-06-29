package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ValidateInputTest {

    @Test
    public void whenInvalidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"one", "1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void whenValidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void whenMultipleValidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "1", "0", "2", "1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int[] data = new int[5];
        data[0] = input.askInt("Enter menu:");
        data[1] = input.askInt("Enter menu:");
        data[2] = input.askInt("Enter menu:");
        data[3] = input.askInt("Enter menu:");
        data[4] = input.askInt("Enter menu:");
        assertArrayEquals(data, new int[]{0, 1, 0, 2, 1});
    }

    @Test
    public void whenNegativeInput()  {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"-1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(-1));
    }
}