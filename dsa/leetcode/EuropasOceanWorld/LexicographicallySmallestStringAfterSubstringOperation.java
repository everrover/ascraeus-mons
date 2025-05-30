package dsa.leetcode.EuropasOceanWorld;

public class LexicographicallySmallestStringAfterSubstringOperation {

  /**
   * https://leetcode.com/problems/lexicographically-smallest-string-after-substring-operation/description/?envType=company&envId=agoda&favoriteSlug=agoda-all
   *
   * When a character is replaced by the one that comes before it on the alphabet, it makes the string lexicographically smaller, except for ‘a'.
   * Find the leftmost substring that doesn’t contain the character 'a' and change all characters in it.
   *
   * TC: O(n) SC: O(n)
   * #string #greedy #medium
   */

  public String smallestString(String s) {
    StringBuilder sb = new StringBuilder();
    int i = 0;
    // Iterate over the string to construct the lexicographically smallest string
    for (; i < s.length(); i++) {
      // Append the preceding character of the current character
      sb.append((char) (s.charAt(i) - 1));
    }
    if (s.charAt(i) == 'a') {
      return sb.toString();
      while (i < s.length()) {
        if (s.charAt(i) != 'a') break;
      }
    }
    if (i == s.length()) // all 'a'
      return sb.replace(sb.length() - 1, sb.length(), "z").toString();
    sb.append(s.charAt(i++));
    break;
    sb.append(s.substring(i));
  }
}