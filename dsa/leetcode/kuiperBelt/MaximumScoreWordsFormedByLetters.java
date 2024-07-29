package dsa.leetcode.kuiperBelt;

class Solution {
  /**
   * https://leetcode.com/problems/maximum-score-words-formed-by-letters/
   *
   * Given a list of words and a list of letters with associated scores, the goal is to calculate the maximum score
   * possible from any combination of the given words. The words can only be formed using each letter once. Use a
   * backtracking approach to explore all possible combinations of words and calculate the best score.
   *
   * TC: O(2^n * m) where n is the number of words and m is the length of the word.
   * SC: O(m) for storing frequency of characters.
   *
   * #array #string #dynamic-programming #backtracking #bit-manipulation #hard
   */
  public int maxScoreWords(String[] words, char[] letters, int[] score) {
    int ans = 0;
    int[] freq = new int[26];
    for (char c : letters) freq[c - 'a']++;
    return backtrack(0, 0, words, freq, score);
  }

  private boolean valid(String word, int[] freq) {
    int[] cfreq = new int[26];
    for (char c : word.toCharArray()) cfreq[c - 'a']++;
    for (int i = 0; i < 26; i++) if (freq[i] < cfreq[i]) return false;
    return true;
  }

  private int backtrack(int curr, int cscore, String[] words, int[] freq, int[] score) {
    if (curr == words.length) return cscore;
    int res = 0;
    if (valid(words[curr], freq)) {
      for (char c : words[curr].toCharArray()) {cscore += score[c - 'a']; freq[c - 'a']--;}
      res = Math.max(res, backtrack(curr + 1, cscore, words, freq, score));
      for (char c : words[curr].toCharArray()) {cscore -= score[c - 'a']; freq[c - 'a']++;}
    }
    res = Math.max(res, backtrack(curr + 1, cscore, words, freq, score));
    return res;
  }
}

/*
Example:
words = ["dog","cat","dad","good"]
letters = ["a","a","c","d","d","d","g","o","o"]
score = [1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0]
Output: 23
Explanation:
Score  a=1, c=9, d=5, g=3, o=2
Given letters, we can form the words "dad" (5+1+5) and "good" (3+2+2+5) with a score of 23.
*/