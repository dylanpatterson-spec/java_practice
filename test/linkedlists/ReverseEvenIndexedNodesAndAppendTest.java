package linkedlists;

import linkedlists.data.SinglyLinkedListNode;

public final class ReverseEvenIndexedNodesAndAppendTest {
    private ReverseEvenIndexedNodesAndAppendTest() {
    }

    public static void main(String[] args) {
        assertList("sample list", new int[] { 2, 4, 6, 5, 3, 1 }, list(1, 2, 3, 4, 5, 6));
        assertList("odd length", new int[] { 2, 4, 3, 1 }, list(1, 2, 3, 4));
        assertList("two nodes", new int[] { 2, 1 }, list(1, 2));
        assertList("single node", new int[] { 7 }, list(7));
        assertList("empty list", new int[] {}, null);
        System.out.println("All ReverseEvenIndexedNodesAndAppend tests passed.");
    }

    private static void assertList(String caseName, int[] expected, SinglyLinkedListNode head) {
        SinglyLinkedListNode result = ReverseEvenIndexedNodesAndAppend.reverseEvenIndexedNodesAndAppend(head);
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