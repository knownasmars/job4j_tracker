package ru.job4j.collection;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JobTest {
    @Test
    public void whenAscByName() {
        List<Job> jobs = Arrays.asList(
                new Job("Mars", 3),
                new Job("Andrey", 2),
                new Job("Petr", 1)
        );
        List<Job> expected = Arrays.asList(
                new Job("Andrey", 2),
                new Job("Mars", 3),
                new Job("Petr", 1)
                );
        Collections.sort(jobs, new JobAscByName());
        assertThat(jobs).isEqualTo(expected);
    }

    @Test
    public void whenAscByPriority() {
        List<Job> jobs = Arrays.asList(
                new Job("Mars", 3),
                new Job("Andrey", 2),
                new Job("Petr", 1)
        );
        List<Job> expected = Arrays.asList(
                new Job("Petr", 1),
                new Job("Andrey", 2),
                new Job("Mars", 3)
        );
        Collections.sort(jobs, new JobAscByPriority());
        assertThat(jobs).isEqualTo(expected);
    }

    @Test
    public void whenDescByName() {
        List<Job> jobs = Arrays.asList(
                new Job("Mars", 3),
                new Job("Andrey", 2),
                new Job("Petr", 1)
        );
        List<Job> expected = Arrays.asList(
                new Job("Petr", 1),
                new Job("Mars", 3),
                new Job("Andrey", 2)
        );
        Collections.sort(jobs, new JobDescByName());
        assertThat(jobs).isEqualTo(expected);
    }

    @Test
    public void whenDescByPriority() {
        List<Job> jobs = Arrays.asList(
                new Job("Petr", 1),
                new Job("Mars", 3),
                new Job("Andrey", 2)
        );
        List<Job> expected = Arrays.asList(
                new Job("Mars", 3),
                new Job("Andrey", 2),
                new Job("Petr", 1)
        );
        Collections.sort(jobs, new JobDescByPriority());
        assertThat(jobs).isEqualTo(expected);
    }

    @Test
    public void whenCompatorByNameLnAndPrority() {
        List<Job> jobs = Arrays.asList(
                new Job("Valentino", 1),
                new Job("Mars", 3),
                new Job("Valentino", 2)
        );
        List<Job> expected = Arrays.asList(
                new Job("Valentino", 1),
                new Job("Valentino", 2),
                new Job("Mars", 3)
        );
        Comparator<Job> cmpNameLnPriority = new JobDescByNameLn().thenComparing(new JobAscByPriority());
        Collections.sort(jobs, cmpNameLnPriority);
        assertThat(jobs).isEqualTo(expected);
    }

    @Test
    public void whenCompatorByPriorityAndName() {
        List<Job> jobs = Arrays.asList(
                new Job("Valentino", 2),
                new Job("Mars", 2),
                new Job("Mars", 1)
        );
        List<Job> expected = Arrays.asList(
                new Job("Mars", 1),
                new Job("Mars", 2),
                new Job("Valentino", 2)
        );
        Comparator<Job> cmpNameLnPriority = new JobAscByPriority().thenComparing(new JobAscByName());
        Collections.sort(jobs, cmpNameLnPriority);
        assertThat(jobs).isEqualTo(expected);
    }
}