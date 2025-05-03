package dsa.leetcode.VallesMarineris;

import java.util.*;

public class Solution {
  /**
   * https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/?envType=daily-question&envId=2025-05-03
   * 
   * Count the frequencies of each number on the top and bottom. Identify potential candidates that can make all
   * values equal in either row by considering common elements with sufficient frequency. Checks for minimum rotations
   * or determine if it is impossible.
   * 
   * TC: O(n) SC: O(1)
   * #array #greedy #medium
   */
  public int minDominoRotations(int[] tops, int[] bottoms) {
    int []topCnt = new int[7];
    int []botCnt = new int[7];
    int []comCnt = new int[7];
    int res= (int)1e5;
    for(int i=0; i<tops.length; i++){
      topCnt[tops[i]]++;
      if(tops[i] == bottoms[i]) comCnt[tops[i]]++;
      else{
        botCnt[bottoms[i]]++;
      }
    }
    for(int i=1; i<=6; i++) {
      if(topCnt[i] + botCnt[i] + comCnt[i] == tops.length) {
        if(Math.min(topCnt[i], botCnt[i]) < res) {
          res = Math.min(topCnt[i], botCnt[i]);
        }
      }
    }
    return res == (int)1e5 ? -1: res;
  }
}