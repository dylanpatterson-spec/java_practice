package stacksandqueues;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class MaxAreaUnderHistogramAfterRemovingOneBarTest {
    private MaxAreaUnderHistogramAfterRemovingOneBarTest() {
    }

    public static void main(String[] args) {
        assertArea("removing a low middle bar bridges the sides", 6, List.of(3, 1, 3));
        assertArea("removal is optional", 10, List.of(2, 1, 5, 6, 2, 3));
        assertArea("two equal bars", 4, List.of(2, 2));
        assertArea("single bar", 5, List.of(5));
        assertArea("zero heights", 0, List.of(0, 0, 0));
        assertArea("empty histogram", 0, List.of());
        assertArea("null histogram", 0, null);
        assertArea("area exceeds int range", 4_294_967_294L, List.of(Integer.MAX_VALUE, Integer.MAX_VALUE));
        assertExhaustiveSmallHistograms();
        assertRandomHistograms();
        System.out.println("All MaxAreaUnderHistogramAfterRemovingOneBar tests passed.");
    }

    private static void assertExhaustiveSmallHistograms() {
        for (int length = 0; length <= 8; length++) {
            int combinationCount = 1;
            for (int index = 0; index < length; index++) {
                combinationCount *= 4;
            }
            for (int encoded = 0; encoded < combinationCount; encoded++) {
                int[] heights = new int[length];
                int remaining = encoded;
                for (int index = 0; index < length; index++) {
                    heights[index] = remaining % 4;
                    remaining /= 4;
                }
                long expected = bruteForceMaximumArea(heights);
                long actual = MaxAreaUnderHistogramAfterRemovingOneBar
                        .maxAreaAfterRemovingOneBar(asList(heights));
                if (actual != expected) {
                    throw new AssertionError("expected " + expected + " for " + format(heights) + ", got " + actual);
                }
            }
        }
    }

    private static long bruteForceMaximumArea(int[] heights) {
        long maximumArea = 0;
        for (int removedIndex = -1; removedIndex < heights.length; removedIndex++) {
            int remainingLength = heights.length - (removedIndex >= 0 ? 1 : 0);
            for (int start = 0; start < remainingLength; start++) {
                int minimumHeight = Integer.MAX_VALUE;
                for (int end = start; end < remainingLength; end++) {
                    int originalIndex = removedIndex >= 0 && end >= removedIndex ? end + 1 : end;
                    minimumHeight = Math.min(minimumHeight, heights[originalIndex]);
                    maximumArea = Math.max(maximumArea, (long) minimumHeight * (end - start + 1));
                }
            }
        }
        return maximumArea;
    }

    private static void assertRandomHistograms() {
        Random random = new Random(14);
        for (int trial = 0; trial < 2_000; trial++) {
            int[] heights = new int[random.nextInt(16)];
            for (int index = 0; index < heights.length; index++) {
                heights[index] = random.nextInt(1_000_001);
            }
            long expected = bruteForceMaximumArea(heights);
            long actual = MaxAreaUnderHistogramAfterRemovingOneBar
                    .maxAreaAfterRemovingOneBar(asList(heights));
            if (actual != expected) {
                throw new AssertionError("expected " + expected + " for " + format(heights) + ", got " + actual);
            }
        }
    }

    private static void assertArea(String caseName, long expected, List<Integer> heights) {
        long actual = MaxAreaUnderHistogramAfterRemovingOneBar.maxAreaAfterRemovingOneBar(heights);
        if (actual != expected) {
            throw new AssertionError(caseName + ": expected " + expected + ", got " + actual);
        }
    }

    private static List<Integer> asList(int[] values) {
        List<Integer> result = new ArrayList<>(values.length);
        for (int value : values) {
            result.add(value);
        }
        return result;
    }

    private static String format(int[] values) {
        StringBuilder result = new StringBuilder("[");
        for (int index = 0; index < values.length; index++) {
            if (index > 0)
                result.append(", ");
            result.append(values[index]);
        }
        return result.append(']').toString();
    }
}