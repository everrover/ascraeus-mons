package dsa.leetcode.VallesMarineris;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/eat-pizzas/description/
 *
 * To maximize the weight gain, we need to strategically select pizzas based on the day (odd or even).
 *
 * - On odd-numbered days, select the largest pizza and three smallest.
 * - On even-numbered days, choose two largest and two smallest pizzas.
 *
 * Using this greedy approach ensures an optimal solution.
 * 
 * earlier thought of only selecting the largest pizza on odd days and two largest on even days. didn't work in
 * case such as `[x x x x x x x x x x 4 4 5 5 5 5]` two 4's would've been picked... but ideally, one 4 and three 5's
 *
 * TC: O(n log n) SC: O(1)
 * #greedy #array #sorting #medium
 */

class EatPizzas {
  public long maxWeight(int[] pizzas) {
    Arrays.sort(pizzas);
    final int n = pizzas.length/4;
    final int candsize = ((n/2) * 3) + (n%2==0?0:1);
    long res = 0;
    int i=4*n-candsize, j=4*n-1, k = 1; 
    while(k<=n){
      if(k%2 == 0) {
        res += pizzas[i];
        i+=2;
      }else{
        res += pizzas[j];
        j--;
      }
      k++;
    }
    return res;
  }
}