package ru.job4j.collection;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class JobSorter {
    public static void main(String[] args) {
        List<Job> jobs = Arrays.asList(
                new Job("1234", 2),
                new Job("123", 4),
                new Job("123456", 1),
                new Job("123", 0)
        );
        Comparator<Job> comb = new JobDescByNameLn()
                .thenComparing(new JobDescByName())
                .thenComparing(new JobDescByPriority());
        Collections.sort(jobs, comb);
        for (Job job : jobs) {
            System.out.println(job);
        }
    }
}