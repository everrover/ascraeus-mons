package dsa.leetcode.EuropasOceanWorld;

public class RegularExpressionMatching {
  /**
   * https://leetcode.com/problems/regular-expression-matching/description/
   *
   * The problem is solved using dynamic programming approach. The solution
   * keeps track of matching substrings using a memoization table to avoid
   * redundant calculations. 
   * 
   * Handles '.' and '*' according to their respective matching rules.
   * Nth fancy, memo over a brute DFS based recursive solution.
   * 
   * TC: O(m * n)  SC: O(m * n)
   * #string #dynamic-programming #recursion #hard
   */

  private int[][] answers;
  private char[] str, pat;

  public boolean isMatch(String s, String p) {
    str = s.toCharArray();
    pat = p.toCharArray();
    answers = new int[s.length() + 1][p.length() + 1];
    return isMatchMemoised(0, 0);
  }

  private boolean isMatchMemoised(int i, int j) {
    if (answers[i][j] != 0) {
      return answers[i][j] == 1;
    }
    if (j == pat.length) {
      answers[i][j] = (i == str.length) ? 1 : -1;
      return answers[i][j] == 1;
    }

    boolean currMatch = i < str.length && (str[i] == pat[j] || pat[j] == '.');

    if ((j + 1) < pat.length && pat[j + 1] == '*') { // match with next *
      answers[i][j] = (isMatchMemoised(i, j + 2) || // ignoring *
        (currMatch && isMatchMemoised(i + 1, j))) ? 1 : -1; // use * with next char in `str`
    } else { // match with next
      answers[i][j] = (currMatch && isMatchMemoised(i + 1, j + 1)) ? 1 : -1;
    }

    return answers[i][j] == 1;
  }
}