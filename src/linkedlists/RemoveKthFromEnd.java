package linkedlists;

import linkedlists.data.SinglyLinkedListNode;

public final class RemoveKthFromEnd {
    private RemoveKthFromEnd() {
    }

    public static SinglyLinkedListNode removeKthFromEnd(SinglyLinkedListNode head, int k) {
        if (k < 0) {
            return head;
        }

        SinglyLinkedListNode sentinel = new SinglyLinkedListNode(0);
        sentinel.next = head;
        SinglyLinkedListNode fast = head;
        for (int index = 0; index < k; index++) {
            if (fast == null) {
                return head;
            }
            fast = fast.next;
        }
        if (fast == null) {
            return head;
        }
        fast = fast.next;

        SinglyLinkedListNode slow = sentinel;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return sentinel.next;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode head = new SinglyLinkedListNode(1);
        head.next = new SinglyLinkedListNode(2);
        head.next.next = new SinglyLinkedListNode(3);
        head.next.next.next = new SinglyLinkedListNode(4);
        head.next.next.next.next = new SinglyLinkedListNode(5);
        SinglyLinkedListNode result = removeKthFromEnd(head, 1);
        for (SinglyLinkedListNode node = result; node != null; node = node.next) {
            System.out.print(node.data + (node.next == null ? "\n" : " -> "));
        }
    }
}