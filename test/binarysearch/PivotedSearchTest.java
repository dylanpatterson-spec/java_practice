package binarysearch;

import java.util.ArrayList;
import java.util.List;

public final class PivotedSearchTest {
    private PivotedSearchTest() {
    }

    public static void main(String[] args) {
        assertIndex("sample target", 4, List.of(4, 5, 6, 7, 0, 1, 2), 0);
        assertIndex("target before pivot", 1, List.of(4, 5, 6, 7, 0, 1, 2), 5);
        assertIndex("target at start", 0, List.of(4, 5, 6, 7, 0, 1, 2), 4);
        assertIndex("target at end", 6, List.of(4, 5, 6, 7, 0, 1, 2), 2);
        assertIndex("missing target", -1, List.of(4, 5, 6, 7, 0, 1, 2), 3);
        assertIndex("unrotated list", 2, List.of(1, 3, 5, 7, 9), 5);
        assertIndex("single element", 0, List.of(4), 4);
        assertIndex("empty list", -1, List.of(), 4);
        assertIndex("null list", -1, null, 4);
        assertAllSmallRotations();
        System.out.println("All PivotedSearch tests passed.");
    }

    private static void assertAllSmallRotations() {
        for (int length = 1; length <= 8; length++) {
            List<Integer> sorted = new ArrayList<>(length);
            for (int index = 0; index < length; index++) {
                sorted.add(index * 3 - 10);
            }
            for (int pivot = 0; pivot < length; pivot++) {
                List<Integer> rotated = new ArrayList<>(length);
                rotated.addAll(sorted.subList(pivot, length));
                rotated.addAll(sorted.subList(0, pivot));
                for (int target = -12; target <= 14; target++) {
                    assertIndex("rotated list " + rotated + ", target " + target,
                            rotated.indexOf(target), rotated, target);
                }
            }
        }
    }

    private static void assertIndex(String caseName, int expected, List<Integer> nums, int target) {
        int actual = PivotedSearch.searchPivoted(nums, target);
        if (expected != actual) {
            throw new AssertionError(caseName + ": expected " + expected + ", got " + actual);
        }
    }
}