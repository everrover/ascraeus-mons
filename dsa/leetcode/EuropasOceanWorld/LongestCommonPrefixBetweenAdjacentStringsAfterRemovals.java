package dsa.leetcode.EuropasOceanWorld;

public class LongestCommonPrefixBetweenAdjacentStringsAfterRemovals {

  /**
   * https://leetcode.com/problems/longest-common-prefix-between-adjacent-strings-after-removals/description/
   *
   * Compute the longest common prefix for adjacent strings after removing each string.
   * Precompute prefix lengths from left to right and right to left arrays.
   * Use precomputed values to compute result efficiently.
   * 
   * TC: O(n * m) SC: O(n)
   * #array #string #medium
   */

  public int[] longestCommonPrefix(String[] words) {
    if(words.length == 1) return new int[]{0};
    if(words.length == 2) return new int[]{0,0};
    
    int[] l2r = new int[words.length];
    int[] r2l = new int[words.length];
    int[] res = new int[words.length];
    int prevMax = 0;

    for (int i = 0; i < words.length - 1; i++) {
      int maxpre = Math.max(prevMax, precomp(words[i + 1], words[i]));
      prevMax = l2r[i] = maxpre;
    }
    l2r[l2r.length - 1] = prevMax;
    
    prevMax = 0;
    for (int i = words.length - 1; i > 0; i--) {
      int maxpre = Math.max(prevMax, precomp(words[i], words[i - 1]));
      prevMax = r2l[i] = maxpre;
    }
    r2l[0] = prevMax;

    for (int i = 0; i < words.length; i++) {
      int lmax = i > 1 ? l2r[i - 2] : 0;
      int rmax = i < (words.length - 2) ? r2l[i + 2] : 0;
      res[i] = Math.max(lmax, rmax);
      if (i > 0 && i < words.length - 1) res[i] = Math.max(res[i], precomp(words[i - 1], words[i + 1]));
    }
    return res;
  }

  private int precomp(String w1, String w2) {
    int i = 0;
    while (i < w1.length() && i < w2.length() && w1.charAt(i) == w2.charAt(i)) i++;
    return i;
  }
}