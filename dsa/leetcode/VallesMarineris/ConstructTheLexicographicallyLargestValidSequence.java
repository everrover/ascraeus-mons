package dsa.leetcode.VallesMarineris;

public class ConstructTheLexicographicallyLargestValidSequence {

  /**
   * https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/description/?envType=daily-question&envId=2025-02-16
   *
   * This solution uses backtracking to construct the sequence by placing integers from n to 1 
   * at possible positions ensuring that the distance between two occurrences of an integer i
   * is exactly i. The sequence is constructed in such a way to ensure it is lexicographically largest.
   *
   * TC: O(n!) SC: O(n)
   * #array #backtracking #medium
   */

  public int[] constructSequence(int n) {
    int[] res = new int[2 * n - 1];
    boolean[] cnt = new boolean[n + 1];

    findPerm(n, 0, cnt, res);
    return res;
  }

  private boolean findPerm(final int n, int idx, boolean[] cnt, int[] res) {
    while (idx < res.length && res[idx] != 0) idx++;
    if (idx == res.length) return true;

    for (int i = n; i >= 1; i--) {
      if (i != 1) {
        if (cnt[i]) continue;
        if (idx + i >= res.length || res[idx + i] != 0) continue;
        res[idx] = res[idx + i] = i;
        cnt[i] = true;
        if (findPerm(n, idx + 1, cnt, res)) return true;
        cnt[i] = false;
        res[idx] = res[idx + i] = 0;
      } else {
        res[idx] = i;
        if (findPerm(n, idx + 1, cnt, res)) return true;
        res[idx] = 0;
      }
    }
    return false;
  }
}