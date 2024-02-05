package dsa.leetcode.kuiperBelt;

public class CountNumberOfHousesAtCertainDistanceI {
  /**
   * https://leetcode.com/problems/count-the-number-of-houses-at-a-certain-distance-i
   * 
   * Required distance is either the distance between the two houses or 
   * the sum of the distances of the two houses from the required house, taking into consideration the warped house
   * 
   * TC O(n^2) SC O(n)
   * #enumeration #distance-calculation #medium #little-logic
   */
  public int[] countOfPairs(int n, int x, int y) {
    if(x>y) return countOfPairs(n, y, x);
    int res[] = new int[n];
    for(int i=1; i<=n; i++){
      for(int j=1; j<i; j++){
        int dist = Math.min(i-j, Math.abs(i-x) + 1 + Math.abs(j-y));
        if(dist>=1) res[dist-1]+=2;
      }
    }
    return res;
  }
}
