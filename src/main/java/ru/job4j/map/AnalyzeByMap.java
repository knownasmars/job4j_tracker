package ru.job4j.map;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double avg = 0D;
        for (Pupil pupil : pupils) {
            double sum = 0D;
            for (Subject subject : pupil.subjects()) {
                sum = sum + subject.getScore();
            }
            avg += sum;
        }
        avg /= pupils.size() * pupils.iterator().next().subjects().size();
        return avg;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> scoreList = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double sum = 0;
            for (Subject subject : pupil.subjects()) {
                sum = sum + subject.getScore();
            }
            double avg = sum / pupil.subjects().size();
            scoreList.add(new Label(pupil.name(), avg));
        }
        return List.copyOf(scoreList);
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        List<Label> scoreList = new ArrayList<>();
        Map<String, Integer> map = new LinkedHashMap<>();
        for (Pupil p : pupils) {
            for (Subject s : p.subjects()) {
                map.putIfAbsent(s.getName(), 0);
                map.put(s.getName(), map.get(s.getName()) + s.getScore());
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            scoreList.add(new Label(entry.getKey(), entry.getValue() / pupils.size()));
        }
        return scoreList;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> scoreList = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double sum = 0;
            for (Subject subject : pupil.subjects()) {
                sum = sum + subject.getScore();
            }
            scoreList.add(new Label(pupil.name(), sum));
        }
        scoreList.sort(Comparator.naturalOrder());
        return scoreList.get(scoreList.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> scoreList = new ArrayList<>();
        Map<String, Integer> map = new LinkedHashMap<>();
        for (Pupil p : pupils) {
            for (Subject s : p.subjects()) {
                map.putIfAbsent(s.getName(), 0);
                map.put(s.getName(), map.get(s.getName()) + s.getScore());
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            scoreList.add(new Label(entry.getKey(), entry.getValue()));
        }
        scoreList.sort(Comparator.naturalOrder());
        return scoreList.get(scoreList.size() - 1);
    }
}