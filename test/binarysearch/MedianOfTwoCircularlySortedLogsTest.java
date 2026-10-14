package binarysearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MedianOfTwoCircularlySortedLogsTest {
    private MedianOfTwoCircularlySortedLogsTest() {
    }

    public static void main(String[] args) {
        assertMedian("provided example", 5L,
                List.of(4L, 5L, 1L, 2L, 3L), List.of(8L, 9L, 6L, 7L));
        assertMedian("even total returns lower middle", 4L,
                List.of(3L, 4L, 1L, 2L), List.of(7L, 8L, 5L, 6L));
        assertMedian("one empty list", 2L, List.of(), List.of(3L, 4L, 1L, 2L));
        assertMedian("overlapping values", 2L, List.of(1L, 3L), List.of(2L, 4L));
        assertMedian("duplicates within and across lists", 1L,
                List.of(1L, 1L, 2L, 1L), List.of(2L, 2L));
        assertMedian("duplicate values across lists", 3L,
                List.of(4L, 1L, 2L, 3L), List.of(2L, 3L, 4L));
        assertMedian("all equal values", 2L, List.of(2L, 2L, 2L), List.of(2L, 2L));
        assertMedian("long endpoints", Long.MIN_VALUE, List.of(Long.MIN_VALUE), List.of(Long.MAX_VALUE));
        assertMedian("single value", 7L, List.of(7L), List.of());
        assertThrows("two empty lists", List.of(), List.of());
        assertThrows("null list", null, List.of(1L));
        assertAllSmallRotations();
        assertAllSmallDuplicateRotations();
        System.out.println("All MedianOfTwoCircularlySortedLogs tests passed.");
    }

    private static void assertAllSmallRotations() {
        for (int firstLength = 0; firstLength <= 5; firstLength++) {
            for (int secondLength = 0; secondLength <= 5; secondLength++) {
                if (firstLength + secondLength == 0) {
                    continue;
                }
                List<Long> firstSorted = makeSortedValues(firstLength, -8);
                List<Long> secondSorted = makeSortedValues(secondLength, -5);
                for (int firstRotation = 0; firstRotation < Math.max(1, firstLength); firstRotation++) {
                    List<Long> first = rotate(firstSorted, firstRotation);
                    for (int secondRotation = 0; secondRotation < Math.max(1, secondLength); secondRotation++) {
                        List<Long> second = rotate(secondSorted, secondRotation);
                        List<Long> merged = new ArrayList<>(first);
                        merged.addAll(second);
                        Collections.sort(merged);
                        long expected = medianOfSorted(merged);
                        long actual = MedianOfTwoCircularlySortedLogs.findMedian(first, second);
                        if (expected != actual) {
                            throw new AssertionError("expected " + expected + " for " + first + " and " + second
                                    + ", got " + actual);
                        }
                    }
                }
            }
        }
    }

    private static void assertAllSmallDuplicateRotations() {
        List<List<Long>> sortedLists = new ArrayList<>();
        sortedLists.add(List.of());
        for (int length = 1; length <= 4; length++) {
            addSortedListsWithDuplicates(new ArrayList<>(), length, sortedLists);
        }

        for (List<Long> firstSorted : sortedLists) {
            for (List<Long> secondSorted : sortedLists) {
                if (firstSorted.isEmpty() && secondSorted.isEmpty()) {
                    continue;
                }
                for (int firstRotation = 0; firstRotation < Math.max(1, firstSorted.size()); firstRotation++) {
                    List<Long> first = rotate(firstSorted, firstRotation);
                    for (int secondRotation = 0; secondRotation < Math.max(1, secondSorted.size()); secondRotation++) {
                        List<Long> second = rotate(secondSorted, secondRotation);
                        List<Long> merged = new ArrayList<>(first);
                        merged.addAll(second);
                        Collections.sort(merged);
                        long expected = medianOfSorted(merged);
                        long actual = MedianOfTwoCircularlySortedLogs.findMedian(first, second);
                        if (expected != actual) {
                            throw new AssertionError("expected " + expected + " for " + first + " and " + second
                                    + ", got " + actual);
                        }
                    }
                }
            }
        }
    }

    private static void addSortedListsWithDuplicates(List<Long> prefix, int targetLength,
            List<List<Long>> sortedLists) {
        if (prefix.size() == targetLength) {
            sortedLists.add(new ArrayList<>(prefix));
            return;
        }

        long minimum = prefix.isEmpty() ? 0 : prefix.get(prefix.size() - 1);
        for (long value = minimum; value <= 2; value++) {
            prefix.add(value);
            addSortedListsWithDuplicates(prefix, targetLength, sortedLists);
            prefix.remove(prefix.size() - 1);
        }
    }

    private static List<Long> makeSortedValues(int length, long start) {
        List<Long> values = new ArrayList<>(length);
        for (int index = 0; index < length; index++) {
            values.add(start + index * 2L);
        }
        return values;
    }

    private static List<Long> rotate(List<Long> values, int pivot) {
        List<Long> rotated = new ArrayList<>(values.size());
        rotated.addAll(values.subList(pivot, values.size()));
        rotated.addAll(values.subList(0, pivot));
        return rotated;
    }

    private static long medianOfSorted(List<Long> values) {
        return values.get((values.size() - 1) / 2);
    }

    private static void assertMedian(String caseName, long expected, List<Long> first, List<Long> second) {
        long actual = MedianOfTwoCircularlySortedLogs.findMedian(first, second);
        if (expected != actual) {
            throw new AssertionError(caseName + ": expected " + expected + ", got " + actual);
        }
    }

    private static void assertThrows(String caseName, List<Long> first, List<Long> second) {
        try {
            MedianOfTwoCircularlySortedLogs.findMedian(first, second);
        } catch (IllegalArgumentException expected) {
            return;
        }
        throw new AssertionError(caseName + ": expected IllegalArgumentException");
    }
}