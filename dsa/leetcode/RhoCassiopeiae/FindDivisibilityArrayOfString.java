package dsa.leetcode.RhoCassiopeiae;

public class FindDivisibilityArrayOfString {
  /**
   * https://leetcode.com/problems/find-the-divisibility-array-of-a-string/submissions/
   *
   * We compute the remainder of the prefix ending at each index.
   * If divisible by m, we set 1; otherwise, 0.
   * `num` is updated = 0, if divisible => full_num = num*10^x+remaining_num
   * if num % m == 0, then div-idx = 1, and divisibility for remaining_num 
   * can be calculated separately.
   * 
   * So to mitigate the overflow in case of large nos, we use long to store 
   * the number and reset it to 0 if divisible by m.
   *
   * TC: O(n) SC: O(n)
   * #array #math #string #medium
   */
   
   public int[] divisibilityArray(String word, int m) {
     char []wd = word.toCharArray();
     int []rez = new int[wd.length];
     long num = 0;
     for(int i=0; i<wd.length; i++){
       num = 10*num+(int)(wd[i]-'0');
       if(num % m == 0){
         rez[i] = 1;  // Mark as divisible
         num = 0;
       } else {
         num %= m;   // Reduce number to remainder
       }
     }
     return rez;
   }
}