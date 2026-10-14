package binarysearch;

import java.util.List;

public final class TargetIndexSearchTest {
    private TargetIndexSearchTest() {
    }

    public static void main(String[] args) {
        assertIndex("sample target", 3, List.of(1, 3, 5, 7, 9), 7);
        assertIndex("first element", 0, List.of(1, 3, 5, 7, 9), 1);
        assertIndex("last element", 4, List.of(1, 3, 5, 7, 9), 9);
        assertIndex("missing middle target", -1, List.of(1, 3, 5, 7, 9), 6);
        assertIndex("target below range", -1, List.of(1, 3, 5), 0);
        assertIndex("target above range", -1, List.of(1, 3, 5), 8);
        assertIndex("single element", 0, List.of(4), 4);
        assertIndex("empty list", -1, List.of(), 4);
        assertIndex("null list", -1, null, 4);
        System.out.println("All TargetIndexSearch tests passed.");
    }

    private static void assertIndex(String caseName, int expected, List<Integer> nums, int target) {
        int actual = TargetIndexSearch.searchTargetIndex(nums, target);
        if (expected != actual) {
            throw new AssertionError(caseName + ": expected " + expected + ", got " + actual);
        }
    }
}