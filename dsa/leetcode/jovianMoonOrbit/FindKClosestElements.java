package dsa.leetcode.jovianMoonOrbit;

import java.util.*;

public class FindKClosestElements {
  /**
   * https://leetcode.com/problems/find-k-closest-elements/
   *
   * Given a sorted integer array, find the k closest integers to x in the array. 
   * The solution involves computing absolute differences with x, and iteratively selecting 
   * the closest elements by expanding outwards from the closest element.
   *
   * TC: O(n) SC: O(k)
   * #array #binary-search #sliding-window #medium
   */
  
  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    int []absarr = new int[arr.length];
    int minval = Integer.MAX_VALUE, minidx = -1;
    for(int i=0; i<arr.length; i++){
      absarr[i] = Math.abs(arr[i]-x);
      if(absarr[i] < minval){
        minval=absarr[i];
        minidx=i;
      }
    }
    int l=minidx-1, r=minidx+1, sz=1;
    while(l>=0 && r<arr.length && sz<k){
      if(absarr[r] < absarr[l]){
        r++;
      }else{
        l--;
      }
      sz++;
    }
    while(l>=0 && sz<k) {l--; sz++;}
    while(r<arr.length && sz<k) {r++; sz++;}
    List<Integer> res = new LinkedList<>();
    for(int i=l+1; i<r; i++){
      res.add(arr[i]);
    }
    return res;
  }
}