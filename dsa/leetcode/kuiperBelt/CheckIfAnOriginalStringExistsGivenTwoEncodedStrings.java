package dsa.leetcode.kuiperBelt;

/*
 * https://leetcode.com/problems/check-if-an-original-string-exists-given-two-encoded-strings/
 * This solution utilizes dynamic programming to explore all possible ways of decoding the given encoded strings.
 * It uses a 3D boolean DP array to store intermediate results for subproblems, defined by current indices in each string and the difference in decoded length.
 * The key idea is to handle digits by trying different lengths they might represent and recurse accordingly, adjusting the difference.
 * TC: Complexity not specified as it depends on number of paths explored
 * SC: O(n^2 * diff) where n is the maximum length of the string and diff is the range of difference between decoded lengths.
 * #dynamic-programming #string #hard
 */

public class CheckIfAnOriginalStringExistsGivenTwoEncodedStrings {
  Boolean[][][] dp = new Boolean[41][41][2002];
  public boolean possiblyEquals(String s1, String s2) {
    char[] chs1 = s1.toCharArray(), chs2 = s2.toCharArray();
    return recurse(chs1, chs2, 0, 0, 0);
  }

  private boolean recurse(char[] chs1, char[] chs2, int r1, int r2, int diff) {
    if (r1 >= chs1.length && r2 >= chs2.length && diff == 0) return true;
    if (dp[r1][r2][diff + 1000] != null) return dp[r1][r2][diff + 1000];
    boolean res = false;
    if (r1 < chs1.length) {
      if (Character.isDigit(chs1[r1])) { // expand unknown chars
        int num = 0, cnt = 0;
        while (r1 + cnt < chs1.length && Character.isDigit(chs1[r1 + cnt]) && cnt < 3) {
          num = num * 10 + (chs1[r1 + cnt] - '0');
          cnt++;
          if (recurse(chs1, chs2, r1 + cnt, r2, diff - num)) res = true;
        }
      } else if (diff > 0) { // expand char to reduce difference
        if (recurse(chs1, chs2, r1 + 1, r2, diff - 1)) res = true;
      } else if (diff == 0 && r2 < chs2.length && chs1[r1] == chs2[r2]) { // both chars same
        if (recurse(chs1, chs2, r1 + 1, r2 + 1, diff)) res = true;
      }
    }
    if (r2 < chs2.length) {
      if (Character.isDigit(chs2[r2])) { // expand unknown chars
        int num = 0, cnt = 0;
        while (r2 + cnt < chs2.length && cnt < 3 && Character.isDigit(chs2[r2 + cnt])) {
          num = num * 10 + (chs2[r2 + cnt] - '0');
          cnt++;
          if (recurse(chs1, chs2, r1, r2 + cnt, diff + num)) res = true;
        }
      } else if (diff < 0) { // expand char to reduce difference
        if (recurse(chs1, chs2, r1, r2 + 1, diff + 1)) res = true;
      }
    }
    return dp[r1][r2][diff + 1000] = res;
  }
}