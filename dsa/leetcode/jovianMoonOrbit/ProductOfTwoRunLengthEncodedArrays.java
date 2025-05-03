package dsa.leetcode.JovianMoonOrbit;

import java.util.*;

/**
 * https://leetcode.com/problems/product-of-two-run-length-encoded-arrays/
 *
 * Expand both encoded arrays into their full forms. Multiply corresponding elements
 * from each and compress the result back into a run-length encoded array.
 * 
 * Since prev num can merge with next num, we need to keep track of the previous number and count.
 *
 * TC: O(n + m) SC: O(n + m)
 * #array #two-pointers #medium
 */

public class ProductOfTwoRunLengthEncodedArrays {
  public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
    int p1 = 0, p2 = 0, c1 = encoded1[0][1], c2 = encoded2[0][1];
    int num1 = encoded1[0][0], num2 = encoded2[0][0], pnum=-1, cnow=0;
    List<List<Integer>> res = new LinkedList<>();

    // Traverse both encoded arrays
    while(p1 < encoded1.length && p2 <encoded2.length) {
      int num = num1*num2, cnt=Math.min(c1,c2);
      if(pnum!=-1 && pnum != num) {
        res.add(List.of(pnum, cnow));
        cnow=0;
      }
      pnum = num;
      cnow += cnt;
      if(c1>c2) {
        p2++;
        c1-=c2;
        c2=encoded2[p2][1];
        num2=encoded2[p2][0];
      } else if(c1<c2) {
        p1++;
        c2-=c1;
        c1=encoded1[p1][1];
        num1=encoded1[p1][0];
      } else {
        p1++;
        p2++;
        if(p1 == encoded1.length || p2 == encoded2.length) continue;
        c2=encoded2[p2][1];
        num2=encoded2[p2][0];
        c1=encoded1[p1][1];
        num1=encoded1[p1][0];
      }
    }
    res.add(List.of(pnum, cnow));
    return res;
  }
}