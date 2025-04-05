package dsa.leetcode.VallesMarineris;

public class CountSpecialIntegers {

    /**
     * https://leetcode.com/problems/count-special-integers/description/
     *
     * OK!
     * 
     * for 2539
     * we have combos
     * x
     * xx
     * xxx
     * 1xxx
     * 1[2-5]xx
     * 15[0-3]x
     * 150[0-9]
     * 
     * In the given ranges, since we've already used the digits that've been used,
     * we can only use the digits that are left. That's why we need to track the digits that are used
     * using a `mark` array.
     * 
     * For any given position, we can have nPr permutation of the digits. That's why
     * we've got fact(9) / fact(9-i+1) for the first digit set of digits: x,xx,xxx
     * 
     * For the next digits, we can have nPr permutation of the digits.
     * pos=0, we've got (n-1)P(10-remaining), can't use digit `0` for first one
     * pos=[1...n-2], we've got nP(10-remaining)
     * pos=[n-1], we've got nP(10-remaining)
     * 
     * If at any point the current digit is already used, we can break the loop. 
     * 
     * I built the solution, earlier, but forgot the solution, so had to reverse-engineer it.
     *
     * TC: O(d^2), where d is the number of digits in n. SC: O(d * 2^d)
     * #math #dynamic-programming #hard
     */
    private static int[] fact = new int[]{1,1,2,6,24,120,720,5040,40320,362880};
    private int[] countAndMark(boolean []mark, int n){
      int one=0, two=0, three=mark[n]?0:1;
      for(int i=0; i<mark.length; i++){
          if(!mark[i]){
              if(i < n){
                  one++;
              }else{
                  two++;
              }
          }
      }
      mark[n] = true;
      return new int[]{one, one+two, three};
    }
    public int countSpecialNumbers(int n) {
      char[] digits = (""+n).toCharArray();
      long res = 0;
      for(int i=1; i<digits.length; i++){
          res += 9*fact[9]/fact[9-i+1];
      }
      boolean[] mark = new boolean[10];
      int []pair = countAndMark(mark, digits[0]-'0');
      if(pair[0] > 1 && digits.length > 1) {
          int curr = pair[0]-1, next = fact[pair[1]-1]/fact[10-digits.length];
          res += curr*next;
      }
      for(int i=1; i<(digits.length-1); i++){
          pair = countAndMark(mark, digits[i]-'0');
          int curr = pair[0], next = fact[pair[1]-1]/fact[10-digits.length];
          res += curr*next;
          if (pair[2] == 0) break;
      }
      if (pair[2] == 1) {
          pair = countAndMark(mark, digits[digits.length - 1] - '0');
          res += pair[0]+pair[2];
      }
      return (int)res;
    }
}