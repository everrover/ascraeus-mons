package dsa.leetcode.VallesMarineris;

public class NumberOfSubarraysWithOddSum {

  /**
   * https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/description/?envType=daily-question&envId=2025-02-25
   *
   * We use the cumulative sum approach to check whether the subarrays have an odd or even sum.
   * If the cumulative sum is odd, increment it with the number of previous even cumulative sums and vice versa.
   * Because that's how the odd-combination is formed, by adding odd number to an even number. And vice versa.
   *
   * TC: O(n) SC: O(1)
   * #array #math #dynamic-programming #prefix-sum #medium
   */

  private final static int MOD = (int)(1e9)+7;
  public int numOfSubarrays(int[] arr) {
    int odd = 0, even = 1, res = 0;
    long total = 0L;
    for(int i=0; i<arr.length; i++){
      total += arr[i];
      if(total%2 == 0){
        res = (res+odd)%MOD;
        even++;
      }else{
        res = (res+even)%MOD;
        odd++;
      }
    }
    return res;
  }
}