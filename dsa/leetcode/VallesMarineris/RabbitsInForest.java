package dsa.leetcode.VallesMarineris;

import java.util.Arrays;

public class RabbitsInForest {

  /**
   * https://leetcode.com/problems/rabbits-in-forest/description/?envType=daily-question&envId=2025-04-20
   *
   * To find the minimum number of rabbits in the forest, group rabbits by their answers.
   * Each group of rabbits with the same answer may contain at most 'answer + 1' rabbits.
   * Keep creating groups until all rabbits are accounted for.
   *
   * TC: O(n log n) due to sorting, SC: O(1)
   * #array #hash-table #math #greedy #medium
   */

  public int numRabbits(int[] answers) {
    int res = 0;
    Arrays.sort(answers);
    int grp = -1, cnt = -1, anscol = -1;
    for(int i=0; i<answers.length; i++){
      int ans = answers[i];
      if(grp != ans) {
        grp = ans;
        cnt = 0;
        res += grp + 1;
      }
      cnt = (cnt + 1) % (grp + 1);
      if (cnt == 0 || i == answers.length - 1) {
        res += grp + 1;
      }
    }
    return res;
  }
}