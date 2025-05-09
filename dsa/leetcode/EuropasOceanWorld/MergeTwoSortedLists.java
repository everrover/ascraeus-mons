package dsa.leetcode.EuropasOceanWorld;

import java.util.*;

public class MergeTwoSortedLists {

  /**
   * https://leetcode.com/problems/merge-two-sorted-lists/description/
   *
   * A standard merge-list problem.
   *
   * TC: O(n + m) SC: O(1)
   * #linked-list #iteration #easy
   */

   public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
      ListNode ans = new ListNode();
      ListNode curr = ans;
      
      while(l1!=null && l2!=null){
          curr.next = new ListNode();
          curr = curr.next;
          if(l1.val > l2.val){
              curr.val = l2.val;
              l2 = l2.next; 
          }else{
              curr.val = l1.val;
              l1 = l1.next; 
          }
      }
      
      while(l1!=null){
          curr.next = new ListNode();
          curr = curr.next;
          curr.val = l1.val;
          l1 = l1.next;
      }
      
      while(l2!=null){
          curr.next = new ListNode();
          curr = curr.next;
          curr.val = l2.val;
          l2 = l2.next; 
      }
      ans = ans.next;
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