package arraysandbasics;

import java.util.ArrayList;
import java.util.List;

public final class MergeIntervals {
    private MergeIntervals() {
    }

    public static List<List<Integer>> mergeHighDefinitionIntervals(List<List<Integer>> intervals) {
        List<List<Integer>> sorted = new ArrayList<>(intervals.size());
        for (List<Integer> interval : intervals) {
            if (interval == null || interval.size() != 2) {
                throw new IllegalArgumentException("Each interval must contain exactly two values");
            }
            sorted.add(new ArrayList<>(interval));
        }
        sorted.sort((first, second) -> Integer.compare(first.get(0), second.get(0)));

        List<List<Integer>> merged = new ArrayList<>();
        for (List<Integer> interval : sorted) {
            if (merged.isEmpty() || interval.get(0) > merged.get(merged.size() - 1).get(1)) {
                merged.add(interval);
            } else {
                List<Integer> lastMerged = merged.get(merged.size() - 1);
                lastMerged.set(1, Math.max(lastMerged.get(1), interval.get(1)));
            }
        }

        return merged;
    }

    public static void main(String[] args) {
        List<List<Integer>> intervals = List.of(
                List.of(1, 3), List.of(2, 6), List.of(8, 10), List.of(15, 18));
        System.out.println(mergeHighDefinitionIntervals(intervals));
    }
}