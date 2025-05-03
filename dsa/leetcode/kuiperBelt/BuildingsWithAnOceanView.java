package dsa.leetcode.KuiperBelt;

import java.util.Stack;

public class BuildingsWithAnOceanView {

  /**
   * https://leetcode.com/problems/buildings-with-an-ocean-view/
   *
   * Traverse the buildings from the nearest to the ocean to the furthest. Keep track of the
   * maximum height seen to the right. If the current building is taller than the maximum height
   * seen so far, it has an ocean view.
   *
   * Alternatively, we can use a top pointer to keep track of the maximum height seen so far. And negate the heights
   * of the buildings to track the buildings with ocean view.
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

  public int[] findBuildingsInplace(int[] heights) {
    int size = 0, pm = heights.length-1; heights[heights.length-1] *= -1;
    for(int i=heights.length-2; i>=0; i--)
      if((-1*heights[pm])<heights[i]){ heights[i] *= -1; size++; pm = i; }
    int []res = new int[size+1]; size = 0;
    for(int i=0; i<heights.length; i++) if(heights[i]<0) { res[size++] = i; heights[i] *= -1; }
    return res;
  }
}