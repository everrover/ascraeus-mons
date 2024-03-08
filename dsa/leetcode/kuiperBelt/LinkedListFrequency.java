package dsa.leetcode.kuiperBelt;

/**
 * https://leetcode.com/problems/linked-list-frequency/
 * This solution uses a hash map like structure (an array) to count the frequency of each element.
 * Initially, we find the maximum value in the list to size our array. Then, we iterate again to fill the array with frequencies.
 * Finally, we construct the result linked list based on the frequencies.
 * 
 * TC: O(n) where n is the number of nodes in the list. SC: O(m) where m is the range of node values.
 * #hash-table #linked-list #counting #medium
 */

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
  public ListNode frequenciesOfElements(ListNode head) {
    int max = 0;
    ListNode curr = head;
    while (curr != null) {
      max = Integer.max(max, curr.val);
      curr = curr.next;
    }
    curr = head;
    int[] cs = new int[max + 1];
    while (curr != null) {
      cs[curr.val]++;
      curr = curr.next;
    }
    ListNode res = new ListNode();
    curr = res;
    for (int c : cs) {
      if (c == 0) continue;
      curr.next = new ListNode(c);
      curr = curr.next;
    }
    return res.next;
  }
}