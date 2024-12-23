package dsa.leetcode.RhoCassiopeiae;

public class AdditiveNumber {

  /**
   * https://leetcode.com/problems/additive-number/
   *
   * Ditto : https://leetcode.com/problems/split-array-into-fibonacci-sequence/ 
   * [LINK][./dsa/leetcode/RhoCassiopeiae/SplitArrayIntoFibonacciSequence.java]
   *
   * TC: O(N^3) SC: O(N)
   * #string #backtracking #medium
   */

  private boolean recurse(int idx, String numfull, long prev, long prev1, long prev2) {
    // Check if the end of the string is reached and if the pattern is valid
    if(idx == numfull.length()) {
      return (prev2 == prev1 + prev) && (prev != -1);
    }
    // Explore all combinations with constraints and backtrack
    for(int i = idx + 1; i <= numfull.length() && i - idx <= 17; i++){
      // Skip numbers with leading zeros, except single zero
      if(numfull.charAt(idx) == '0' && i - idx >= 2) break;
      long curr = Long.valueOf(numfull.substring(idx, i));
      // Condition to ensure no overflow and valid sequence
      if(curr > Long.MAX_VALUE) break;
      if(prev2 != -1 && prev1 != -1 && curr != prev2 + prev1) continue;
      boolean rec = recurse(i, numfull, prev1, prev2, curr);
      if(rec) return true;
    }
    return false;
  }

  public boolean isAdditiveNumber(String num) {
    // Return false if length of num is less than 3
    if(num.length() < 3) return false;
    return recurse(0, num, -1, -1, -1);
  }
}