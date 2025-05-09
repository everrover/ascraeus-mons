package dsa.leetcode.EuropasOceanWorld;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class RemoveNthNodeFromEndOfList {

    /**
     * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
     * 
     * Maintain two pointers with a gap of n nodes. Move both until the first pointer reaches end,
     * then delete the nth node from the end by adjusting the second pointer.
     * 
     * Foundational problem for linked list manipulation - tortoise and hare algorithm.
     * 
     * TC: O(n) SC: O(1)
     * #linked-list #two-pointers #medium
     */

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode first = dummy, second = dummy;
        // Advance first n+1 steps ahead
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        // Skip the desired node
        second.next = second.next.next;
        return dummy.next;
    }
}