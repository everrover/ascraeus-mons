package dsa.leetcode.kuiperBelt;

import java.util.Stack;

public class BuildingsWithAnOceanView {

  /**
   * https://leetcode.com/problems/buildings-with-an-ocean-view/
   *
   * Traverse the buildings from the nearest to the ocean to the furthest. Keep track of the
   * maximum height seen to the right. If the current building is taller than the maximum height
   * seen so far, it has an ocean view.
   * 
   * TC: O(n) SC: O(n)
   * #array #stack #monotonic-stack #medium
   */
  
  public int[] findBuildings(int[] heights) {
    Stack<Integer> st = new Stack<>();
    for (int i = heights.length - 1; i >= 0; i--) {
      if (st.isEmpty() || heights[i] > heights[st.peek()]) {
        st.push(i);
      }
    }
    int[] res = new int[st.size()];
    for (int i = 0; i < res.length; i++) {
      res[i] = st.pop();
    }
    return res;
  }
}