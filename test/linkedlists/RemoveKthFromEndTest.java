package linkedlists;

import linkedlists.data.SinglyLinkedListNode;

public final class RemoveKthFromEndTest {
    private RemoveKthFromEndTest() {
    }

    public static void main(String[] args) {
        assertList("remove middle node", new int[] { 1, 2, 3, 5 }, list(1, 2, 3, 4, 5), 1);
        assertList("remove head", new int[] { 2, 3 }, list(1, 2, 3), 2);
        assertList("remove tail", new int[] { 1, 2 }, list(1, 2, 3), 0);
        assertList("remove only node", new int[] {}, list(7), 0);
        assertInvalidK("negative k", list(1, 2), -1, new int[] { 1, 2 });
        assertInvalidK("k equals length", list(1, 2), 2, new int[] { 1, 2 });
        assertInvalidK("k exceeds length", list(1, 2), 3, new int[] { 1, 2 });
        System.out.println("All RemoveKthFromEnd tests passed.");
    }

    private static void assertList(String caseName, int[] expected, SinglyLinkedListNode head, int k) {
        SinglyLinkedListNode result = RemoveKthFromEnd.removeKthFromEnd(head, k);
        for (int index = 0; index < expected.length; index++) {
            if (result == null || result.data != expected[index]) {
                throw new AssertionError(caseName + ": expected " + format(expected) + ", got " + format(result));
            }
            result = result.next;
        }
        if (result != null) {
            throw new AssertionError(caseName + ": expected " + format(expected) + ", got extra nodes");
        }
    }

    private static void assertInvalidK(String caseName, SinglyLinkedListNode head, int k, int[] expected) {
        SinglyLinkedListNode result = RemoveKthFromEnd.removeKthFromEnd(head, k);
        if (result != head || !format(result).equals(format(expected))) {
            throw new AssertionError(caseName + ": expected original list " + format(expected)
                    + ", got " + format(result));
        }
    }

    private static SinglyLinkedListNode list(int... values) {
        SinglyLinkedListNode sentinel = new SinglyLinkedListNode(0);
        SinglyLinkedListNode tail = sentinel;
        for (int value : values) {
            tail.next = new SinglyLinkedListNode(value);
            tail = tail.next;
        }
        return sentinel.next;
    }

    private static String format(int[] values) {
        StringBuilder result = new StringBuilder("[");
        for (int index = 0; index < values.length; index++) {
            if (index > 0)
                result.append(", ");
            result.append(values[index]);
        }
        return result.append(']').toString();
    }

    private static String format(SinglyLinkedListNode node) {
        StringBuilder result = new StringBuilder("[");
        while (node != null) {
            if (result.length() > 1)
                result.append(", ");
            result.append(node.data);
            node = node.next;
        }
        return result.append(']').toString();
    }
}