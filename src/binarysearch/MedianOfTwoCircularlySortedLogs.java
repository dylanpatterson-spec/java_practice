package binarysearch;

import java.util.List;

public final class MedianOfTwoCircularlySortedLogs {
    private MedianOfTwoCircularlySortedLogs() {
    }

    public static long findMedian(List<Long> first, List<Long> second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Input lists must not be null.");
        }
        if (first.isEmpty() && second.isEmpty()) {
            throw new IllegalArgumentException("At least one input list must contain a value.");
        }

        int firstPivot = findPivot(first);
        int secondPivot = findPivot(second);
        if (first.size() > second.size()) {
            List<Long> temporaryList = first;
            first = second;
            second = temporaryList;
            int temporaryPivot = firstPivot;
            firstPivot = secondPivot;
            secondPivot = temporaryPivot;
        }

        int firstSize = first.size();
        int secondSize = second.size();
        int leftSize = (firstSize + secondSize + 1) / 2;
        int low = Math.max(0, leftSize - secondSize);
        int high = Math.min(firstSize, leftSize);

        while (low <= high) {
            int firstCut = low + (high - low) / 2;
            int secondCut = leftSize - firstCut;
            long firstLeft = firstCut == 0 ? Long.MIN_VALUE
                    : sortedValue(first, firstPivot, firstCut - 1);
            long firstRight = firstCut == firstSize ? Long.MAX_VALUE
                    : sortedValue(first, firstPivot, firstCut);
            long secondLeft = secondCut == 0 ? Long.MIN_VALUE
                    : sortedValue(second, secondPivot, secondCut - 1);
            long secondRight = secondCut == secondSize ? Long.MAX_VALUE
                    : sortedValue(second, secondPivot, secondCut);

            if (firstLeft <= secondRight && secondLeft <= firstRight) {
                return Math.max(firstLeft, secondLeft);
            }
            if (firstLeft > secondRight) {
                high = firstCut - 1;
            } else {
                low = firstCut + 1;
            }
        }

        throw new IllegalArgumentException("Each input must be a rotation of a sorted list.");
    }

    private static int findPivot(List<Long> values) {
        for (int index = 1; index < values.size(); index++) {
            if (values.get(index - 1) > values.get(index)) {
                return index;
            }
        }
        return 0;
    }

    private static long sortedValue(List<Long> values, int pivot, int sortedIndex) {
        int sourceIndex = (int) (((long) pivot + sortedIndex) % values.size());
        return values.get(sourceIndex);
    }

    public static void main(String[] args) {
        List<Long> first = List.of(4L, 5L, 1L, 2L, 3L);
        List<Long> second = List.of(8L, 9L, 6L, 7L);
        System.out.println(findMedian(first, second));
    }
}