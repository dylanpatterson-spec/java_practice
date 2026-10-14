package stacksandqueues;

import java.util.List;

public final class NextGreaterElementWithPositionOffsetTest {
    private NextGreaterElementWithPositionOffsetTest() {
    }

    public static void main(String[] args) {
        assertResult("sample readings", List.of(
                List.of(4, 3), List.of(2, 1), List.of(4, 1), List.of(-1, -1), List.of(-1, -1)),
                List.of(2, 1, 2, 4, 3));
        assertResult("increasing readings", List.of(
                List.of(2, 1), List.of(3, 1), List.of(-1, -1)), List.of(1, 2, 3));
        assertResult("decreasing readings", List.of(
                List.of(-1, -1), List.of(-1, -1), List.of(-1, -1)), List.of(3, 2, 1));
        assertResult("equal values are not greater", List.of(
                List.of(-1, -1), List.of(-1, -1)), List.of(2, 2));
        assertResult("negative readings", List.of(
                List.of(-1, 1), List.of(-1, -1), List.of(-1, -1)), List.of(-3, -1, -2));
        assertResult("empty readings", List.of(), List.of());
        System.out.println("All NextGreaterElementWithPositionOffset tests passed.");
    }

    private static void assertResult(String caseName, List<List<Integer>> expected, List<Integer> readings) {
        List<List<Integer>> actual = NextGreaterElementWithPositionOffset
                .nextGreaterElementWithPositionOffset(readings);
        if (!expected.equals(actual)) {
            throw new AssertionError(caseName + ": expected " + expected + ", got " + actual);
        }
    }
}