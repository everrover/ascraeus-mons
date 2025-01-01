package dsa.leetcode.fermi_s_paradox;

public class MaximumScoreAfterSplittingAString {
  /**
   * https://leetcode.com/problems/maximum-score-after-splitting-a-string/description/?envType=daily-question&envId=2025-01-01
   *
   * To maximize the score of a split, traverse the string and maintain a tally for zeros on the left
   * and ones on the right. Precompute ones and use a linear pass to resolve.
   * 
   * TC: O(n) SC: O(n)
   * #string #prefix-sum #easy
   */
  
  public int maxScore(String s) {
    int res = 0;
    int[] pre = new int[s.length()];
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '1') pre[i]++;
      if (i > 0) pre[i] = pre[i - 1];
    }
    for (int i = 0; i < s.length() - 1; i++) {
      int z = i + 1 - pre[i];
      int o = pre[s.length() - 1] - pre[i];
      res = Math.max(res, o + z);
      // System.out.println(pre[i] + ":" + o + ":" + z);
    }
    return res;
  }
}