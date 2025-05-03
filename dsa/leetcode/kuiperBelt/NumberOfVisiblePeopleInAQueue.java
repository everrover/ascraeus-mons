package dsa.leetcode.KuiperBelt;

import java.util.Stack;

/**
 * https://leetcode.com/problems/number-of-visible-people-in-a-queue/
 *
 * The approach uses a monotonic stack to keep track of heights as we iterate from the end of the queue to the start.
 * For each person, we count how many people are shorter until we find one taller and mark them as visible.
 *
 * Started iteration from left->right and wasn't able to find the count of reqd elements. So tried with right->left and, voila!
 * 
 * TC: O(n) SC: O(n)
 * #monotonic-stack #array #hard
 */
public class NumberOfVisiblePeopleInAQueue {
  public int[] canSeePersonsCount(int[] heights) {
    int i = heights.length - 1;
    Stack<Integer> stack = new Stack<>();
    int res[] = new int[heights.length];
    while(i >= 0) {
      int h = heights[i], cnt = 0;
      while(!stack.isEmpty() && h > stack.peek()){
        stack.pop(); cnt++;
      }
      res[i--] = cnt + (stack.isEmpty() ? 0 : 1);
      stack.push(h);
    }
    return res;
  }
}