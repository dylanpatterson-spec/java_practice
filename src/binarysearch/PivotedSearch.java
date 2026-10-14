package binarysearch;

import java.util.List;

public final class PivotedSearch {
    private PivotedSearch() {
    }

    public static int searchPivoted(List<Integer> nums, int target) {
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

            int lowValue = nums.get(low);
            int highValue = nums.get(high);
            if (lowValue <= middleValue) {
                if (lowValue <= target && target < middleValue) {
                    high = middle - 1;
                } else {
                    low = middle + 1;
                }
            } else if (middleValue < target && target <= highValue) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        List<Integer> nums = List.of(4, 5, 6, 7, 0, 1, 2);
        System.out.println(searchPivoted(nums, 0));
    }
}