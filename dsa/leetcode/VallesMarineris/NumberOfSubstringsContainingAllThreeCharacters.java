package dsa.leetcode.VallesMarineris;

public class NumberOfSubstringsContainingAllThreeCharacters {

  /**
   * https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/?envType=daily-question&envId=2025-03-11
   *
   * Utilize a sliding window approach. Traverse the string while maintaining a count of 'a', 'b', and 'c'.
   * Count valid substrings whenever all counts are at least 1, and adjust the window's position accordingly.
   *
   * TC: O(n), SC: O(1)
   * #hash-table #string #sliding-window #medium
   */

  public int numberOfSubstrings(String s) {
    int[] cnts = new int[3];
    int l = 0, r = 0, res = 0;
    char[] chs = s.toCharArray();

    while (r < chs.length) {
      if (chs[r] == 'a') cnts[0]++;
      else if (chs[r] == 'b') cnts[1]++;
      else if (chs[r] == 'c') cnts[2]++;

      while (l <= r && cnts[0] >= 1 && cnts[1] >= 1 && cnts[2] >= 1) {
        res += chs.length - r;
        if (chs[l] == 'a') cnts[0]--;
        else if (chs[l] == 'b') cnts[1]--;
        else if (chs[l] == 'c') cnts[2]--;
        l++;
      }
      r++;
    }
    return res;
  }
}