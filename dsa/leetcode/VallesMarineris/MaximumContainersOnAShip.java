package dsa.leetcode.VallesMarineris;

public class MaximumContainersOnAShip {

  /**
   * https://leetcode.com/problems/maximum-containers-on-a-ship/description/
   *
   * Calculate the maximum number of containers that can be placed on the
   * ship's deck without exceeding the maximum weight capacity. Each container
   * has a specific weight, and the total number of containers cannot exceed
   * the number of available spaces on the deck.
   *
   * TC: O(1) SC: O(1)
   * #math #greedy #easy
   */

      public int maxContainers(int n, int w, int maxWeight) {
          return Math.min(maxWeight / w, n * n);
      }
}