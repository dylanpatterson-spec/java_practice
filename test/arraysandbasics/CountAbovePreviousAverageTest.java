package arraysandbasics;

import java.util.List;

public final class CountAbovePreviousAverageTest {
    private CountAbovePreviousAverageTest() {
    }

    public static void main(String[] args) {
        assertCount("sample input", 4, List.of(10, 20, 30, 25, 40));
        assertCount("full prefix average", 2, List.of(1, 100, 60));
        assertCount("strictly greater", 1, List.of(1, 3, 2));
        assertCount("empty list", 0, List.of());
        assertCount("single element", 0, List.of(7));
        System.out.println("All CountAbovePreviousAverage tests passed.");
    }

    private static void assertCount(String caseName, int expected, List<Integer> responseTimes) {
        int actual = CountAbovePreviousAverage.countElementsGreaterThanPreviousAverage(responseTimes);
        if (actual != expected) {
            throw new AssertionError(caseName + ": expected " + expected + ", got " + actual);
        }
    }
}