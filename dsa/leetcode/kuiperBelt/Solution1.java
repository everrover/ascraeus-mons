package dsa.leetcode.kuiperBelt;

import java.util.Arrays;

public class Solution1 {
  public static void main(String[] args) {

    // ["RangeModule","addRange","removeRange","queryRange","queryRange","queryRange"]
    //[[],[10,20],[14,16],[10,14],[13,15],[16,17]]
    RM rm = new RM();
    rm.addRange(10, 20);
    rm.removeRange(14, 16);
    System.out.println(rm.queryRange(10, 14)); // true\
    System.out.println(rm.queryRange(13, 15)); // false
    System.out.println(rm.queryRange(16, 17)); // true

  }
}

/*
["BookMyShow","gather","gather","scatter","scatter"]
[[2,5},{4,0},{2,0},{5,1},{5,1]]
*/

/**
 * Your BookMyShow object will be instantiated and called as such:
 * BookMyShow obj = new BookMyShow(n, m);
 * int[] param_1 = obj.gather(k,maxRow);
 * boolean param_2 = obj.scatter(k,maxRow);
 */
