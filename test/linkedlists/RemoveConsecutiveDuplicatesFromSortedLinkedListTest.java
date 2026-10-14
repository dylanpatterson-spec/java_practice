package linkedlists;

import linkedlists.data.SinglyLinkedListNode;

public final class RemoveConsecutiveDuplicatesFromSortedLinkedListTest {
    private RemoveConsecutiveDuplicatesFromSortedLinkedListTest() {
    }

    public static void main(String[] args) {
        assertList("duplicate runs", new int[] { 1, 2, 3, 4 }, list(1, 1, 2, 3, 3, 3, 4));
        assertList("all duplicate values", new int[] { 5 }, list(5, 5, 5));
        assertList("no duplicates", new int[] { 1, 2, 3 }, list(1, 2, 3));
        assertList("single node", new int[] { 7 }, list(7));
        assertList("empty list", new int[] {}, null);
        assertFirstNodesRetained();
        System.out.println("All RemoveConsecutiveDuplicatesFromSortedLinkedList tests passed.");
    }

    private static void assertFirstNodesRetained() {
        SinglyLinkedListNode first = new SinglyLinkedListNode(1);
        first.next = new SinglyLinkedListNode(1);
        SinglyLinkedListNode second = new SinglyLinkedListNode(2);
        first.next.next = second;
        second.next = new SinglyLinkedListNode(2);
        SinglyLinkedListNode third = new SinglyLinkedListNode(3);
        second.next.next = third;
        third.next = new SinglyLinkedListNode(3);

        SinglyLinkedListNode result = RemoveConsecutiveDuplicatesFromSortedLinkedList.deleteDuplicates(first);
        if (result != first || result.next != second || second.next != third || third.next != null) {
            throw new AssertionError("the first node of each duplicate run was not retained");
        }
    }

    private static void assertList(String caseName, int[] expected, SinglyLinkedListNode head) {
        SinglyLinkedListNode result = RemoveConsecutiveDuplicatesFromSortedLinkedList.deleteDuplicates(head);
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