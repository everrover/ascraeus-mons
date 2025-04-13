package dsa.leetcode.JupitersGreatStorm;

public class FindClosestPerson {

  /**
   * https://leetcode.com/problems/find-closest-person/description/
   * 
   * Compare the distances from Persons 1 and 2 to Person 3 to determine the answer.
   * Return 1 if Person 1 arrives first, 2 if Person 2 arrives first, or 0 if both arrive at the same time.
   *
   * TC: O(1) SC: O(1)
   * #math #comparison #easy
   */

  public int findClosest(int x, int y, int z) {
    // Calculate the absolute differences in distance
    return Math.abs(z - x) == Math.abs(z - y) ? 0 : (Math.abs(z - x) > Math.abs(z - y) ? 2 : 1);
  }
}