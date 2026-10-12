package stacksandqueues;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public final class NextGreaterElementWithPositionOffset {
    private NextGreaterElementWithPositionOffset() {
    }

    public static List<List<Integer>> nextGreaterElementWithPositionOffset(List<Integer> readings) {
        List<Integer> values = new ArrayList<>(readings);
        List<List<Integer>> result = new ArrayList<>(values.size());
        for (int index = 0; index < values.size(); index++) {
            result.add(List.of(-1, -1));
        }
        Deque<Integer> pendingIndices = new ArrayDeque<>();

        for (int index = 0; index < values.size(); index++) {
            while (!pendingIndices.isEmpty() && values.get(index) > values.get(pendingIndices.peek())) {
                int previousIndex = pendingIndices.pop();
                result.set(previousIndex, List.of(values.get(index), index - previousIndex));
            }
            pendingIndices.push(index);
        }

        return result;
    }

    public static void main(String[] args) {
        List<Integer> readings = List.of(2, 1, 2, 4, 3);
        List<List<Integer>> result = nextGreaterElementWithPositionOffset(readings);
        System.out.println(result);
    }
}