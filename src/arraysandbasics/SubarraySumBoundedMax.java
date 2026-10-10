package arraysandbasics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class SubarraySumBoundedMax {
    private SubarraySumBoundedMax() {
    }

    public static long countSubarrays(List<Integer> nums, long k, long M) {
        Map<Long, Long> prefixCounts = new HashMap<>();
        prefixCounts.put(0L, 1L);
        long prefixSum = 0;
        long count = 0;

        for (int num : nums) {
            if (num > M) {
                prefixSum = 0;
                prefixCounts.clear();
                prefixCounts.put(0L, 1L);
                continue;
            }

            prefixSum += num;
            count += prefixCounts.getOrDefault(prefixSum - k, 0L);
            prefixCounts.merge(prefixSum, 1L, Long::sum);
        }

        return count;
    }

    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 1, 3, 1, 2);
        System.out.println(countSubarrays(nums, 3, 2));
    }
}