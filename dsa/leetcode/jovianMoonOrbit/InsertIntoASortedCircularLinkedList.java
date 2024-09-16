package dsa.leetcode.jovianMoonOrbit;

// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _next) {
        val = _val;
        next = _next;
    }
}

/**
 * https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/
 * 
 * The solution traverses the circular linked list to find the correct position for insertion.
 * If the list is empty, it creates a new node and points it to itself.
 * Otherwise, it finds the correct spot either by comparing values in a sorted manner or by
 * identifying the cycle when values wrap around. After identifying the insertion point, it
 * adjusts the links to insert the new node.
 * 
 * TC: O(n) SC: O(1)
 * #linked-list #insertion #medium
 */

public class Solution {
    public Node insert(Node head, int insertVal) {
        Node n = new Node(insertVal);
        if (head == null) {
            n.next = n;
            head = n;
        } else {
            Node cur = head;
            while (true) {
                if (cur.val < cur.next.val) { // sorted order
                    if (cur.val <= insertVal && insertVal <= cur.next.val) break;
                } else if (cur.val > cur.next.val) { // sort cycle
                    if (cur.val <= insertVal || insertVal <= cur.next.val) break;
                } else { // 1 node
                    if (cur.next == head) break;
                }
                cur = cur.next;
            }
            n.next = cur.next;
            cur.next = n;
        }
        return head;
    }
}