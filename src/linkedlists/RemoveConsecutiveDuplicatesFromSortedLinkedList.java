package linkedlists;

import linkedlists.data.SinglyLinkedListNode;

public final class RemoveConsecutiveDuplicatesFromSortedLinkedList {
    private RemoveConsecutiveDuplicatesFromSortedLinkedList() {
    }

    public static SinglyLinkedListNode deleteDuplicates(SinglyLinkedListNode head) {
        SinglyLinkedListNode current = head;
        while (current != null && current.next != null) {
            if (current.data == current.next.data) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode head = new SinglyLinkedListNode(1);
        head.next = new SinglyLinkedListNode(1);
        head.next.next = new SinglyLinkedListNode(2);
        head.next.next.next = new SinglyLinkedListNode(3);
        head.next.next.next.next = new SinglyLinkedListNode(3);
        head = deleteDuplicates(head);

        for (SinglyLinkedListNode node = head; node != null; node = node.next) {
            System.out.print(node.data + (node.next == null ? "\n" : " -> "));
        }
    }
}