package dsa.leetcode.VallesMarineris;

import java.util.Arrays;

public class RabbitsInForest {

  /**
   * https://leetcode.com/problems/rabbits-in-forest/description/?envType=daily-question&envId=2025-04-20
   *
   * To find the minimum number of rabbits in the forest, group rabbits by their answers.
   * Each group of rabbits with the same answer may contain at most 'answer + 1' rabbits.
   * WHY? Otherwise, we'd have inconsistent rabbits mapped to colors...
   * 
   * Consider: 1 1 1 2 3 3
   * If we assign 0, color RED, only 1 more rabbit can be RED. If 1 is RED, then 2 must be BLUE[or vice-versa].
   * If 3-5 were RED w.r.t. above answer would be inconsistent.
   * So ... [0,1] -> RED [2] -> BLUE, [3,a,b] -> GREEN, [4,5,x,y] -> YELLOW => 11 rabbits.
   * 
   * Here a,b,x,y are the rabbits with answers 2,3,3 respectively but weren't questioned.
   *
   * TC: O(n log n) due to sorting, SC: O(1)
   * #array #hash-table #math #greedy #medium
   */

  public int numRabbits(int[] answers) {
    int res = 0;
    Arrays.sort(answers);
    int grp = -1, cnt = -1;
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