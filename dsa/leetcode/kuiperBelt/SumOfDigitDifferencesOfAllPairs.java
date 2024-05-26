package dsa.leetcode.kuiperBelt;

/**
 * https://leetcode.com/problems/sum-of-digit-differences-of-all-pairs/
 * To solve this, for each digit position within the numbers, count the occurrences of each digit (0-9).
 * The contribution of each position towards the final sum involves calculating contributions for each digit,
 * based on the count of that digit and the count of other digits in the same position.
 * 
 * TC: O(N*D + 10*D^2) assuming N is the length of input array and D is the number of digits.
 * SC: O(D*10) for storing the counts.
 * #array #hash-table #math #counting #medium
 */
public class SumOfDigitDifferencesOfAllPairs {

  public long sumDigitDifferences(int[] nums) {
    long res = 0;
    int x = nums[0], cnt = 0;
    boolean zeroenc = false;
    while(x > 0 && !zeroenc){
      x /= 10;
      cnt++;
    }
    int [][]counts = new int[cnt][10];
    for(int i=0; i<nums.length; i++){
      int num = nums[i];
      for(int j=0; j<cnt; j++){
        counts[j][num % 10]++;
        num /= 10;
      }
    }
    for(int i=0; i<cnt; i++){
      for(int j=0; j<counts[i].length; j++){
        if(counts[i][j] == 0) continue;
        for(int k=j+1; k<counts[i].length; k++){
          if(counts[i][k] == 0) continue;
          res += (long)counts[i][j] * counts[i][k];
        }
      }
    }
    return res;
  }
}