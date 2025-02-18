package dsa.leetcode.VallesMarineris;

public class ConstructSmallestNumberFromDiString {
  
  /**
   * https://leetcode.com/problems/construct-smallest-number-from-di-string/description/?envType=daily-question&envId=2025-02-18
   *
   * Generate permutations until we find the smallest number following the 'I' and 'D' pattern. 
   * Use backtracking to explore all potential numbers.
   *
   * TC: O(n!) SC: O(n)
   * #string #backtracking #stack #greedy #medium
   */
  
  public String constructSmallestNumberFromDIString(String pattern) {
    boolean[] used = new boolean[10];
    return dfs(0, pattern, new StringBuilder(), 0, used);
  }
  
  private String dfs(int idx, String pattern, StringBuilder newstr, int prev, boolean[] used) {
    if (idx == pattern.length() + 1) {
      return newstr.toString();
    }
    for (int i = 1; i <= 9; i++) {
      if (used[i]) continue;
      if ((idx == 0 || (pattern.charAt(idx - 1) == 'I' && prev < i) || (pattern.charAt(idx - 1) == 'D' && prev > i))) {
        newstr.append(i);
        used[i] = true;
        String res = dfs(idx + 1, pattern, newstr, i, used);
        if (res != null) return res;
        used[i] = false;
        newstr.deleteCharAt(newstr.length() - 1);
      }
    }
    return null;
  }
}