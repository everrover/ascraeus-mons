package dsa.leetcode.VallesMarineris;

public class AddTwoNumbers {
  
  /**
   * https://leetcode.com/problems/add-two-numbers/
   *
   * Traverse through both linked lists. Add corresponding digits and manage carry. Create new nodes for sum.
   * Return the head of the resulting linked list containing the sum.
   * 
   * TC: O(max(m, n)) SC: O(max(m, n))
   * #linked-list #math #recursion #medium
   */
  
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode current = dummyHead;
    int carry = 0;
    while (l1 != null || l2 != null) {
      int x = (l1 != null) ? l1.val : 0;
      int y = (l2 != null) ? l2.val : 0;
      int sum = carry + x + y;
      carry = sum / 10;
      current.next = new ListNode(sum % 10);
      current = current.next;
      if (l1 != null) l1 = l1.next;
      if (l2 != null) l2 = l2.next;
    }
    if (carry > 0) {
      current.next = new ListNode(carry);
    }
    return dummyHead.next;
  }
}

class ListNode {
  int val;
  ListNode next;
  ListNode(int x) { val = x; }
}