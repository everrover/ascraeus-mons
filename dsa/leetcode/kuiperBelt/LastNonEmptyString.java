package dsa.leetcode.kuiperBelt;

public class LastNonEmptyString {
  /**
   * https://leetcode.com/problems/apply-operations-to-make-string-empty/
   * The approach involves counting frequency of each character and keeping only those
   * which have the highest frequency. Ultimately keeping last occurrences of them.
   * 
   * TC: O(n), where n is the length of the string.
   * SC: O(1), constant space due to finite charset size.
   * #frequency-count #string #medium
   */
  public String lastNonEmptyString(String s) {
    int[] chs = new int[26];
    char[] chars = s.toCharArray();
    for (char c : chars) {
      chs[c - 'a']++;
    }
    int lg = 0;
    for (int c : chs) lg = Integer.max(lg, c);
    StringBuilder sb = new StringBuilder();
    for (int i = chars.length - 1; i >= 0; i--) {
      char j = chars[i];
      if (chs[j - 'a'] == lg) {
        chs[j - 'a'] = 0;
        sb.append(j);
      }
    }
    return sb.reverse().toString();
  }
}