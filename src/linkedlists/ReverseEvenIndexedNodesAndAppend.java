package linkedlists;

import linkedlists.data.SinglyLinkedListNode;

public final class ReverseEvenIndexedNodesAndAppend {
    private ReverseEvenIndexedNodesAndAppend() {
    }

    public static SinglyLinkedListNode reverseEvenIndexedNodesAndAppend(SinglyLinkedListNode head) {
        SinglyLinkedListNode retainedSentinel = new SinglyLinkedListNode(0);
        SinglyLinkedListNode retainedTail = retainedSentinel;
        SinglyLinkedListNode reversedEven = null;
        SinglyLinkedListNode current = head;
        int index = 0;

        while (current != null) {
            SinglyLinkedListNode next = current.next;
            if (index % 2 == 0) {
                current.next = reversedEven;
                reversedEven = current;
            } else {
                retainedTail.next = current;
                retainedTail = current;
                retainedTail.next = null;
            }
            current = next;
            index++;
        }

        if (retainedTail == retainedSentinel) {
            return reversedEven;
        }
        retainedTail.next = reversedEven;
        return retainedSentinel.next;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode head = new SinglyLinkedListNode(1);
        SinglyLinkedListNode tail = head;
        for (int value = 2; value <= 6; value++) {
            tail.next = new SinglyLinkedListNode(value);
            tail = tail.next;
        }

        SinglyLinkedListNode result = reverseEvenIndexedNodesAndAppend(head);
        for (SinglyLinkedListNode node = result; node != null; node = node.next) {
            System.out.print(node.data + (node.next == null ? "\n" : " -> "));
        }
    }
}