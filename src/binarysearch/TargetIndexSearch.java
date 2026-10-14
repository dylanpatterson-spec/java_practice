package binarysearch;

import java.util.List;

public final class TargetIndexSearch {
    private TargetIndexSearch() {
    }

    public static int searchTargetIndex(List<Integer> nums, int target) {
        if (nums == null) {
            return -1;
        }

        int low = 0;
        int high = nums.size() - 1;
        while (low <= high) {
            int middle = low + (high - low) / 2;
            int middleValue = nums.get(middle);
            if (middleValue == target) {
                return middle;
            }
            if (middleValue < target) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 3, 5, 7, 9);
        System.out.println(searchTargetIndex(nums, 7));
    }
}