package arraysandbasics;

import java.util.List;

public final class CountAbovePreviousAverage {
    private CountAbovePreviousAverage() {
    }

    public static int countElementsGreaterThanPreviousAverage(List<Integer> responseTimes) {
        int count = 0;
        long previousSum = 0;

        for (int index = 0; index < responseTimes.size(); index++) {
            if (index > 0 && (long) responseTimes.get(index) * index > previousSum) {
                count++;
            }
            previousSum += responseTimes.get(index);
        }

        return count;
    }

    public static void main(String[] args) {
        List<Integer> responseTimes = List.of(10, 20, 30, 25, 40);
        System.out.println(countElementsGreaterThanPreviousAverage(responseTimes));
    }
}