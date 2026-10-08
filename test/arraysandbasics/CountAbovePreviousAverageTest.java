package arraysandbasics;

public final class CountAbovePreviousAverageTest {
    private CountAbovePreviousAverageTest() {
    }

    public static void main(String[] args) {
        assertCount("sample input", 4, new int[] {10, 20, 30, 25, 40});
        assertCount("full prefix average", 2, new int[] {1, 100, 60});
        assertCount("strictly greater", 1, new int[] {1, 3, 2});
        assertCount("empty array", 0, new int[0]);
        assertCount("single element", 0, new int[] {7});
        System.out.println("All CountAbovePreviousAverage tests passed.");
    }

    private static void assertCount(String caseName, int expected, int[] values) {
        int actual = CountAbovePreviousAverage.countElementsGreaterThanPreviousAverage(values);
        if (actual != expected) {
            throw new AssertionError(caseName + ": expected " + expected + ", got " + actual);
        }
    }
}