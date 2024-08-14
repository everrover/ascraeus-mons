package dsa.leetcode.kuiperBelt;

// imports here if needed

public class TrappingRainWater {
  /**
   * https://leetcode.com/problems/trapping-rain-water/
   *
   * Calculates the amount of trapped rain water in the given elevation map.
   * Uses dynamic programming to store the maximum height to the left and right of each bar.
   * 
   * We could use a two-pointer approach to solve this problem as well with a monotonic stack
   * via left->right and right->left iterations.
   *
   * TC: O(n) SC: O(n)
   * #array #two-pointers #dynamic-programming #stack #hard
   */
  public int trap(int[] hts) {
    if (hts.length < 3) return 0;
    int[] maxL = new int[hts.length], maxR = new int[hts.length];

    maxL[0] = hts[0];
    for (int i = 1; i < hts.length; i++) {
      maxL[i] = Integer.max(maxL[i-1], hts[i]);
    }

    maxR[hts.length-1] = hts[hts.length-1];
    for (int i = hts.length-2; i >= 0; i--) {
      maxR[i] = Integer.max(maxR[i+1], hts[i]);
    }

    int water = 0;
    for (int i = 0; i < hts.length; i++) {
      water += Integer.min(maxL[i], maxR[i]) - hts[i];
    }
    return water;
  }
}