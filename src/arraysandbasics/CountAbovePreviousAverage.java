package arraysandbasics;

public final class CountAbovePreviousAverage {
    private CountAbovePreviousAverage() {
    }

    public static int countElementsGreaterThanPreviousAverage(int[] values) {
        int count = 0;
        long previousSum = 0;

        for (int index = 0; index < values.length; index++) {
            if (index > 0 && (long) values[index] * index > previousSum) {
                count++;
            }
            previousSum += values[index];
        }

        return count;
    }

    public static void main(String[] args) {
        int[] values = {10, 20, 30, 25, 40};
        System.out.println(countElementsGreaterThanPreviousAverage(values));
    }
}