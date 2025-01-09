package dsa.leetcode.mithrim_montez;

class Solution {
  /**
   * https://leetcode.com/problems/counting-words-with-a-given-prefix/description/?envType=daily-question&envId=2025-01-09
   *
   * Iterate through each word in the array and check if the prefix matches the start of the word.
   * Increment the counter for every match found.
   *
   * TC: O(n * m), where n is the number of words and m is the length of the prefix
   * SC: O(1)
   * #array #string #string-matching #easy
   */
  public int prefixCount(String[] words, String pref) {
    int res = 0;
    for (String word : words) {
      if (word.length() >= pref.length() && word.substring(0, pref.length()).equals(pref)) {
        res++;
      }
    }
    return res;
  }
}