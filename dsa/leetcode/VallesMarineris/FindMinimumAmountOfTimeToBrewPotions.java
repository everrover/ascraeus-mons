package dsa.leetcode.VallesMarineris;

public class FindMinimumAmountOfTimeToBrewPotions {

  /**
   * https://leetcode.com/problems/find-the-minimum-amount-of-time-to-brew-potions/description/
   *
   * Maintain each wizard's earliest free time for the last potion as f[i]. Start from now = f[0], and update now = max(now + skill[i - 1] * mana[j], f[i]) for each wizard. The final f[n - 1] becomes now + skill[n - 1] * mana[j] for each potion.
   *
   * TC: O(n*m) SC: O(n)
   * #array #simulation #prefix-sum #medium
   */

  public long minTime(int[] skill, int[] mana) {
    long[] pre = new long[skill.length];
    long[] ss = new long[skill.length];
    ss[0] = (long)skill[0]*mana[0];
    for(int i=1; i<mana.length; i++){
      long mst = Math.max(0L, ss[0]);
      pre[i] = pre[i-1] + skill[i-1];
      for(int j=1; j<skill.length; j++){
        ss[j] = ss[j-1] + mana[i] * skill[j];
      }
      ss[0] = mst + mana[i] * skill[0];
    }
    return ss[skill.length-1];
  }

}