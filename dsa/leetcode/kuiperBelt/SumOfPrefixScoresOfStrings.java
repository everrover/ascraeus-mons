package dsa.leetcode.KuiperBelt;

class SumOfPrefixScoresOfStrings {

  /**
   * https://leetcode.com/problems/sum-of-prefix-scores-of-strings/
   *
   * Use a Trie data structure. Insert all the words into it, and keep a counter at each node that will tell you how
   * many times we have visited each prefix.
   * This allows us to efficiently retrieve the score of each prefix during traversal.
   *
   * TC: O(N * M) SC: O(K)
   * #trie #string #hard
   */

  private static class TN {
    char ch;
    int cnt;
    TN[] tns;

    public TN(char c) {
      tns = new TN[26];
      ch = c;
      cnt = 0;
    }

    public void addWord(char[] chs) {
      TN t = this;
      for (int i = 0; i < chs.length; i++) {
        int idx = chs[i] - 'a';
        if (t.tns[idx] == null) {
          t.tns[idx] = new TN(chs[i]);
        }
        t.tns[idx].cnt++;
        t = t.tns[idx];
      }
    }

    public int traverse(char[] chs) {
      int res = 0;
      TN t = this;
      for (int i = 0; i < chs.length; i++) {
        int idx = chs[i] - 'a';
        res += t.tns[idx].cnt;
        t = t.tns[idx];
      }
      return res;
    }
  }
  public int[] sumPrefixScores(String[] words) {
    TN root = new TN('.');
    int[] res = new int[words.length];
    for (String word : words) root.addWord(word.toCharArray());
    int i = 0;
    for (String word : words) res[i++] = root.traverse(word.toCharArray());
    return res;
  }
}