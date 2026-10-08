package arraysandbasics;

import java.util.ArrayList;
import java.util.List;

public final class MergeIntervalsTest {
    private MergeIntervalsTest() {
    }

    public static void main(String[] args) {
        assertMerged("overlapping intervals",
                List.of(List.of(1, 6), List.of(8, 10), List.of(15, 18)),
                List.of(List.of(1, 3), List.of(2, 6), List.of(8, 10), List.of(15, 18)));
        assertMerged("unsorted and nested intervals",
                List.of(List.of(1, 10)),
                List.of(List.of(5, 7), List.of(1, 10), List.of(2, 3)));
        assertMerged("touching intervals",
                List.of(List.of(1, 5)),
                List.of(List.of(1, 2), List.of(2, 5)));
        assertMerged("empty input", List.of(), List.of());
        assertMerged("no overlaps",
                List.of(List.of(1, 2), List.of(4, 5)),
                List.of(List.of(4, 5), List.of(1, 2)));
        System.out.println("All MergeIntervals tests passed.");
    }

    private static void assertMerged(String caseName, List<List<Integer>> expected, List<List<Integer>> intervals) {
        List<List<Integer>> original = copy(intervals);
        List<List<Integer>> actual = MergeIntervals.mergeHighDefinitionIntervals(intervals);
        if (!expected.equals(actual)) {
            throw new AssertionError(caseName + ": expected " + expected + ", got " + actual);
        }
        if (!original.equals(intervals)) {
            throw new AssertionError(caseName + ": input intervals were modified");
        }
    }

    private static List<List<Integer>> copy(List<List<Integer>> intervals) {
        List<List<Integer>> copy = new ArrayList<>();
        for (List<Integer> interval : intervals) {
            copy.add(new ArrayList<>(interval));
        }
        return copy;
    }
}