package dsa.leetcode.VallesMarineris;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/product-of-the-last-k-numbers/description/?envType=daily-question&envId=2025-02-14
 *
 * Solution:
 * Keep all prefix products of numbers in an array, then calculate the product of last K elements in O(1) complexity.
 * When a zero number is added, clean the array of prefix products. I am using a zeromark to keep track of the last zero.
 * And set the prefix product to 1 for the next number.
 * 
 * TC: O(1) for both add and getProduct methods
 * SC: O(n)
 * #array #math #design #data-stream #prefix-sum #medium
 */

public class ProductOfTheLastKNumbers {
    private List<Long> pre;
    private int n, zeromark = -1;
    public ProductOfTheLastKNumbers() {
      this.pre = new ArrayList<>(1000);
      pre.add(1L);
      this.n = 1;
    }
    
    public void add(int num) {
      long res = 0L;
      if(num == 0){
        res = 1L;
        this.zeromark = n;
      }else{
        res = num*pre.get(n-1);
      }
      this.pre.add(res);
      this.n++;
    }
    
    public int getProduct(int k) {
      if(n-k-1 < zeromark) return 0;
      // System.out.println(k+":"+pre+":"+pre.get(n-k-1));
      int res = (int)(pre.get(n-1)/pre.get(n-k-1));
      return res;
    }
  }
  
  /**
   * Your ProductOfNumbers object will be instantiated and called as such:
   * ProductOfNumbers obj = new ProductOfNumbers();
   * obj.add(num);
   * int param_2 = obj.getProduct(k);
   */