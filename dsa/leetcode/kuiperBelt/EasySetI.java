package dsa.leetcode.kuiperBelt;

import java.util.*;

public class EasySetI {
  /**
   * https://leetcode.com/problems/find-champion-i/description/
   * If any team wins all the matches it plays, it is called a champion. 
   * Vice-versa if a team loses even one of the matches it plays, it is called a loser.
   * 
   * #array #easy
   * @param grid : 2D array of 0s and 1s
   * @return
   */
  public int findChampion(int[][] grid) {
    for(var i=0; i<grid.length; i++){
      boolean doesWin = true;
      for(var j=0; j<grid.length; j++){
        if(i!=j) {doesWin &= (grid[i][j] == 1);}
        // else if(i!=j && grid[i][j] == 1) teams[i] = ;
      }
      if(doesWin) return i;
    }
    return -1;
  }

  /**
   * https://leetcode.com/problems/find-the-winner-of-the-circular-game/
   * 
   * For each iteration, we find occurance of kth bit set in iteration instance.
   * 
   * #bit-manipulation #easy
   * 
   * @param nums
   * @param k
   * @return
   */
  public int findKOr(int[] nums, int k) {
    boolean[] bits = new boolean[31];
    for (int bit = 0; bit < 31; bit++) {
        int count = 0;
        for (int num : nums) {
            if ((num & (1 << bit)) != 0) {
                count++;
            }
        }
        if (count >= k) {
            bits[bit] = true;
        }
    }

    int res = 0;
    for (int bit = 0; bit < 31; bit++) {
        if (bits[bit]) {
            res |= (1 << bit);
        }
    }
    return res;
  }

  /**
   * https://leetcode.com/problems/minimum-sum-of-mountain-triplets-i/description/
   * 
   * Simple brute force solution.
   * 
   * #array #easy
   * @param nums
   * @return
   */
  public int minimumSum(int[] nums) {
    var res = Integer.MAX_VALUE;
    for(int i=0; i<nums.length-2; i++){
      for(int j=i+1; j<nums.length-1; j++){
        for(int k=j+1; k<nums.length; k++){
          if(nums[j] > nums[i] && nums[j] > nums[k]) {
            res = Math.min(res, nums[i]+nums[j]+nums[k]);
          }
        }
      }
    }
    return res==Integer.MAX_VALUE?-1:res;
  }

  /**
   * https://leetcode.com/problems/find-indices-with-index-and-value-difference-i/description/
   * 
   * Simple brute force solution.
   * 
   * #array #easy
   */
  public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
    for(int i=0; i<nums.length; i++){
      for(int j=i; j<nums.length; j++){
        if(Math.abs(j-i)>=indexDifference && Math.abs(nums[j]-nums[i])>=valueDifference){
          return new int[]{i,j};
        }
      }
    }
    return new int[]{-1,-1};
  }

  /**
   * https://leetcode.com/problems/maximum-odd-binary-number/submissions/
   * 
   * To satisfy we put in all ones in beginning(except one since we have to keep the number odd) and then all zeros.
   * 
   * #string #easy
   */
  public String maximumOddBinaryNumber(String s) {
    int cnt = 0, zcnt = 0;
    for(int i=0; i<s.length(); i++) if(s.charAt(i) == '1') cnt++; else zcnt++;
    StringBuilder sb = new StringBuilder();
    for(int i=1; i<cnt; i++) sb.append(1);
    for(int i=0; i<zcnt; i++) sb.append(0);
    sb.append(1);
    return sb.toString();
  }

  /**
   * https://leetcode.com/problems/count-symmetric-integers/description/
   * 
   * Applied simple brute force.
   * 
   * #digit-manipulation #easy
   * @param low
   * @param high
   * @return
   */
  public int countSymmetricIntegers(int low, int high) {
    int count = 0;
    for(int i=low; i<=high; i++){
      if (isSymmetric(i)){
        count++;
      }
    }
    return count;
  }
  private boolean isSymmetric(int num) {
    String strNum = Integer.toString(num);
    if (strNum.length() % 2 != 0) {
      return false;
    }
    int halfSize = strNum.length() / 2;
    int firstHalfSum = 0, secondHalfSum = 0;
    for (int i = 0; i < halfSize; i++) {
      firstHalfSum += strNum.charAt(i) - '0';
    }
    for (int i = halfSize; i < strNum.length(); i++){
      secondHalfSum += strNum.charAt(i)  - '0';
    }

    return firstHalfSum == secondHalfSum;
  }

  /**
   * https://leetcode.com/problems/account-balance-after-rounded-purchase/description/
   * 
   * 0-4 => 0
   * 5-9 => 10
   * 
   * num = (num/10)[10th digit] + (num%10)[1st digit and becomes carry]
   * num_we_want = num + (x%10)[need to add to carry to make it 10]
   *             = (num/10) + (num%10+x)%10
   * The x we want is 5 which allows us to do it one step
   * 
   * #math #easy
   */
  public int accountBalanceAfterPurchase(int purchaseAmount) {
    return 100-((purchaseAmount+5)/10)*10;
  }

  /**
   * https://leetcode.com/problems/check-if-array-is-good/description/
   * 
   * Simple brute force solution. Hashing can also be used.
   * 
   * #array #easy #sorting #hashing
   * 
   * @param nums
   * @return
   */
  public boolean isGood(int[] nums) {
    final int size = nums.length-1;
    if(size == 0) return false;
    Arrays.sort(nums);
    int i=0;
    for(; i<size; i++){
      if(nums[i]!=i+1) return false;
    }
    return nums[i] == size && nums[i] == nums[i-1];
  }

  /**
   * https://leetcode.com/problems/split-strings-by-separator/description/
   * 
   * Simple brute force solution.
   * 
   * #string #easy
   * @param words
   * @param separator
   * @return
   */
  public List<String> splitWordsBySeparator(List<String> words, char separator) {
    String sep = "\\"+separator;
    final String empty = "";
    List<String> res = new LinkedList<>();
    for(String word: words){
      for(String spl: word.split(sep)){
        if(spl.equals(empty)) continue;
        res.add(spl);
      }
    }
    return res;  
  }

  /**
   * https://leetcode.com/problems/sum-of-squares-of-special-elements/description/
   * 
   * Simple brute force solution.
   * 
   * #array #easy
   * @param nums
   */
  public int sumOfSquares(int[] nums) {
    int res = 0;
    for(var i=0; i<nums.length; i++){
      if(nums.length%(i+1) == 0) res += nums[i]*nums[i];
    }
    return res;
  }
}