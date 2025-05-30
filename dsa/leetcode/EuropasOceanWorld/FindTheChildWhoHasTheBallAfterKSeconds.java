package dsa.leetcode.EuropasOceanWorld;

public class FindTheChildWhoHasTheBallAfterKSeconds {
  
  /**
   * https://leetcode.com/problems/find-the-child-who-has-the-ball-after-k-seconds/description/?envType=company&envId=agoda&favoriteSlug=agoda-all
   *
   * The ball will return to child 0 after 2 * (n - 1) seconds and everything is the same as time 0.
   * The answer for k is the same as the answer for k % (2 * (n - 1)).
   *
   * TC: O(1) SC: O(1)
   * #math #simulation #easy
   */
  
  public int numberOfChild(int n, int k) {
    if (k <= n - 1) return k;
    int cycles = k / (n - 1);
    int mod = k % (n - 1);
    // If cycles are odd return opposite end, else mod
    return cycles % 2 == 1 ? (n - mod - 1) : mod;
  }
}
// 0 1 2 3 4