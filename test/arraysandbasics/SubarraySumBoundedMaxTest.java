package arraysandbasics;

import java.util.List;

public final class SubarraySumBoundedMaxTest {
    private SubarraySumBoundedMaxTest() {
    }

    public static void main(String[] args) {
        assertCount("sample input", 3, List.of(1, 2, 1, 3, 1, 2), 3, 2);
        assertCount("maximum is inclusive", 1, List.of(2, 1), 3, 2);
        assertCount("values above bound split subarrays", 1, List.of(1, 4, 2, 1), 3, 3);
        assertCount("negative values", 2, List.of(2, -1, 2), 1, 2);
        assertCount("long target sum", 1, List.of(Integer.MAX_VALUE, Integer.MAX_VALUE),
                4_294_967_294L, Integer.MAX_VALUE);
        assertCount("long maximum", 1, List.of(1, 2), 3, 3_000_000_000L);
        assertCount("empty input", 0, List.of(), 3, 2);
        System.out.println("All SubarraySumBoundedMax tests passed.");
    }

    private static void assertCount(String caseName, long expected, List<Integer> nums, long k, long M) {
        long actual = SubarraySumBoundedMax.countSubarrays(nums, k, M);
        if (actual != expected) {
            throw new AssertionError(caseName + ": expected " + expected + ", got " + actual);
        }
    }
}