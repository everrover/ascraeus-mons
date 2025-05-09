package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class MergeTwoSortedLists {

  /**
   * https://leetcode.com/problems/merge-two-sorted-lists/description/
   *
   * Iteratively merge two sorted linked lists by comparing their nodes' values.
   * Append the smaller node to the new list and move the pointer forward.
   * Continue the process until either list is exhausted, then append the remaining nodes.
   *
   * TC: O(n + m) SC: O(1)
   * #linked-list #iteration #easy
   */

  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode ans = new ListNode(0);  // Placeholder for the result
    ListNode curr = ans;

    while(l1 != null && l2 != null) {
      if(l1.val < l2.val) {
        curr.next = new ListNode();
        curr = curr.next;
        curr.val = l1.val;
        l1 = l1.next;
      } else {
        curr.next = new ListNode();
        curr = curr.next;
        curr.val = l2.val;
        l2 = l2.next;
      }
    }

    while(l1 != null) {  // Append remaining nodes of l1
      curr.next = new ListNode();
      curr = curr.next;
      curr.val = l1.val;
      l1 = l1.next;
    }

    while(l2 != null) {  // Append remaining nodes of l2
      curr.next = new ListNode();
      curr = curr.next;
      curr.val = l2.val;
      l2 = l2.next;
    }

    ans = ans.next;  // Skip the placeholder node
    return ans;
  }

  private class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}