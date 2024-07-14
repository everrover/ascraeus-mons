package dsa.leetcode.kuiperBelt;

/*
 * https://leetcode.com/problems/check-if-an-original-string-exists-given-two-encoded-strings/
 *
 * diff = extra chars in s1 - extra chars in s2
 * Only when this difference is 0, can we check if chs1[r1] == chs2[r2] or not.
 * Since we can only remove chars from s1 & s2 sequentially from either side.
 * Max range of nums is 40 so max diff can be -1000...1000.
 *
 * dp(r1, r2, diff) = true | r1==chs1.length, r2==chs2.length, diff==0
 *                  = dp(r1+cnt, r2, diff+num(r1...r1+cnt)) | diff<0, chs1[r1...r1+cnt] are numeric, cnt<3 | check with all possible number combos
 *                  = dp(r1+1, r2, diff-1) | diff>0, chs1[r1] is char | reduce diff by 1 by expending 1 char
 *                  = dp(r1+1, r2+1, diff) | diff==0, chs1[r1] == chs2[r2] | both chars same
 *                  = dp(r1, r2+cnt, diff-num(r2...r2+cnt)) | diff<0, chs2[r2...r2+cnt] are numeric, cnt<3 | check with all possible number combos
 *                  = dp(r1, r2+1, diff+1) | diff<0, chs2[r2...r2+cnt] are numeric, cnt<3 | reduce diff by 1 by expending 1 char
 *                  = false | otherwise
 *
 *
 * I put memoization on this bottom-up recursion i.e. DFS
 * I put up some extra cases which caused me to get WA, corrected them and voila!
 *
 * TC: O(n^2*(2*max-diff))
 * SC: O(n^2 * (2*max-diff))
 *
 * #dynamic-programming #string #hard #recursion #tricky
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