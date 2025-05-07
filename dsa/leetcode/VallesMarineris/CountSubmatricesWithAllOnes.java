package dsa.leetcode.VallesMarineris;

import java.util.*;

public class CountSubmatricesWithAllOnes {

  /**
   * https://leetcode.com/problems/count-submatrices-with-all-ones/description/
   *
   * ~ logic as in [LINK](./FindSortedSubmatricesWithMaximumElementAtMostK.java)
   *
   * TC: O(n^2) SC: O(n^2)
   * #array #dynamic-programming #stack #matrix #monotonic-stack #medium
   */

   public int numSubmat(int[][] grid) {
    int res = 0;
    final int m = grid.length, n = grid[0].length;
    int [][]inc = new int[n][m];
    for(int i=0; i<m; i++) inc[n-1][i] = grid[i][n-1]==0? -1:1;
    for(int j=n-2; j>=0; j--){
      for(int i=0; i<m; i++){
        if(grid[i][j] == 0) {
          inc[j][i] = -1;
        }else if(grid[i][j+1] == 0) {
          inc[j][i] = 1;
        }else {
          inc[j][i] = inc[j+1][i]+1;
        }
      }
    }
    for(int j=n-1; j>=0; j--){
      res += helper(inc[j]);
    }
    return res;
  }
  private int helper(int[] A) {

    int[] sum = new int[A.length];
    Stack<Integer> stack = new Stack<>();
    int split = -1;
    for (int i = 0; i < A.length; ++i) {

      if(A[i] <= 0) {stack.clear(); split = i; continue;}
      while (!stack.isEmpty() && A[stack.peek()] >= A[i]) stack.pop();

      if (!stack.isEmpty()) {
        int preIndex = stack.peek();
        sum[i] = sum[preIndex];
        sum[i] += A[i] * (i - preIndex);
      } else {
        sum[i] = A[i] * (i - split);
      }

      stack.push(i);
    }

    int res = 0;
    for (int s : sum) res += s;

    return res;
  }
}