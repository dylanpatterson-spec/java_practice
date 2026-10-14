package stacksandqueues;

import java.util.Arrays;
import java.util.List;

public final class MaxAreaUnderHistogramAfterRemovingOneBar {
    private MaxAreaUnderHistogramAfterRemovingOneBar() {
    }

    public static long maxAreaAfterRemovingOneBar(List<Integer> heights) {
        if (heights == null || heights.isEmpty()) {
            return 0;
        }

        int[] values = new int[heights.size()];
        int valueIndex = 0;
        for (int height : heights) {
            values[valueIndex++] = height;
        }

        MinSegmentTree minimums = new MinSegmentTree(values);
        long maximumArea = 0;
        int length = values.length;

        for (int index = 0; index < length; index++) {
            int height = values[index];
            if (height <= 0) {
                continue;
            }

            int leftLower = minimums.rightmostBelow(0, index - 1, height);
            int leftSecondLower = leftLower < 0 ? -1 : minimums.rightmostBelow(0, leftLower - 1, height);
            int rightLower = minimums.leftmostBelow(index + 1, length - 1, height);
            int rightSecondLower = rightLower < 0 ? -1 : minimums.leftmostBelow(rightLower + 1, length - 1, height);

            int normalLeft = leftLower + 1;
            int normalRight = rightLower < 0 ? length - 1 : rightLower - 1;
            maximumArea = Math.max(maximumArea, (long) height * (normalRight - normalLeft + 1));

            if (leftLower >= 0) {
                int left = leftSecondLower + 1;
                int right = rightLower < 0 ? length - 1 : rightLower - 1;
                maximumArea = Math.max(maximumArea, (long) height * Math.max(0, right - left));
            }
            if (rightLower >= 0) {
                int left = leftLower + 1;
                int right = rightSecondLower < 0 ? length - 1 : rightSecondLower - 1;
                maximumArea = Math.max(maximumArea, (long) height * Math.max(0, right - left));
            }
        }

        return maximumArea;
    }

    private static final class MinSegmentTree {
        private final int length;
        private final int leafCount;
        private final int[] minimum;

        private MinSegmentTree(int[] values) {
            length = values.length;
            int leaves = 1;
            while (leaves < length) {
                leaves *= 2;
            }
            leafCount = leaves;
            minimum = new int[leafCount * 2];
            Arrays.fill(minimum, Integer.MAX_VALUE);
            System.arraycopy(values, 0, minimum, leafCount, length);
            for (int index = leafCount - 1; index > 0; index--) {
                minimum[index] = Math.min(minimum[index * 2], minimum[index * 2 + 1]);
            }
        }

        private int rightmostBelow(int queryLeft, int queryRight, int threshold) {
            if (queryLeft > queryRight) {
                return -1;
            }
            return rightmostBelow(1, 0, leafCount - 1, queryLeft, queryRight, threshold);
        }

        private int rightmostBelow(int node, int left, int right, int queryLeft, int queryRight, int threshold) {
            if (right < queryLeft || left > queryRight || minimum[node] >= threshold) {
                return -1;
            }
            if (left == right) {
                return left < length ? left : -1;
            }
            int middle = left + (right - left) / 2;
            int found = rightmostBelow(node * 2 + 1, middle + 1, right, queryLeft, queryRight, threshold);
            return found >= 0 ? found
                    : rightmostBelow(node * 2, left, middle, queryLeft, queryRight, threshold);
        }

        private int leftmostBelow(int queryLeft, int queryRight, int threshold) {
            if (queryLeft > queryRight) {
                return -1;
            }
            return leftmostBelow(1, 0, leafCount - 1, queryLeft, queryRight, threshold);
        }

        private int leftmostBelow(int node, int left, int right, int queryLeft, int queryRight, int threshold) {
            if (right < queryLeft || left > queryRight || minimum[node] >= threshold) {
                return -1;
            }
            if (left == right) {
                return left < length ? left : -1;
            }
            int middle = left + (right - left) / 2;
            int found = leftmostBelow(node * 2, left, middle, queryLeft, queryRight, threshold);
            return found >= 0 ? found
                    : leftmostBelow(node * 2 + 1, middle + 1, right, queryLeft, queryRight, threshold);
        }
    }

    public static void main(String[] args) {
        List<Integer> heights = List.of(3, 1, 3);
        System.out.println(maxAreaAfterRemovingOneBar(heights));
    }
}