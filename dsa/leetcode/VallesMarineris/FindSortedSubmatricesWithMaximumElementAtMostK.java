package dsa.leetcode.VallesMarineris;

import java.util.Stack;

/**
 * https://leetcode.com/problems/find-sorted-submatrices-with-maximum-element-at-most-k/
 * 
 * Toook me damned two days, to figure out the logic... wonder why we get stuck on wrong path sometimes...
 * 
 * Use a monotonic stack to keep track of the longest increasing subarray
 * of each row that can be extended on a given column.
 * Iterate through each row checking conditions for forming submatrices.
 * TRIED the first set_A first, which takes O(n^2) time for each column which behaves like a histogram, where
 * we find the count of all rectangles in the histogram. For each column, if we apply the logic via set_A, O(m*n^2) time
 * If we apply the logic via set_B, O(m*n) time. But here we need to track sum for each column at each row.
// ----------------------------------
s = -1
1 1 1      = 3 x (0-st.peek+1)  ; 0
1 1        = 2 x 2  ; 1
1          = 1 x 3  ; 2
1 1 1      = 1 x 4 + (3-1) x 1 => 1 x 3 + 3 x (3 - 2)  ; 2 3 // Set_A = Set_B ❗️
           = w.r.t each index, find the max height of each rectangle with each height value in the monotonic stack
                               => sum[prev_peak] + curr_max_ht*(curr_idx - prev_peak)
1 1        = 1 x 5 + (2-1) x 2  ; 1,0 2,3
1 1 1      = 1 x 6 + (2-1) x 3 + (3-2) x 1 ; 1,0 2,3 3,5
1 1        = 1 x 7 + (2-1) x 4  ; 1,0 2,3
1 1 1 1    = 1 x 8 + (2-1) x 5 + (4-2) x 1; 1,0 2,3 4,7
1 1 1 1    = 1 x 9 + (2-1) x 6 + (4-2) x 2 = 1 x 3 + 2 x 4 + 4 x 2; 1,0 2,3 4,7 // Set_A = Set_B
1 1        = 1 x 10 + (2-1) x 7  ; 1,0 2,3
1 1 1      = 1 x 11 + (2-1) x 8 + (3-2) x 1; 1,0 2,3 3,10
// -----------------------------------
; 0,-1
1 1 1      = 3 x sum-of-n(0-st.peek+1)  ; 3,0
1 1        = 2 x 2  ; 2,0
1          = 1 x 3  ; 1,0
1 1 1      = 1 x 4 + (3-1) x 1  ; 1,0 3,3
1 1        = 1 x 5 + (2-1) x 2  ; 1,0 2,3 <- new 2,4 < 3,3 => 2(new),3(retained)
1          = 1 x 6  ; 1,0
1 1        = 1 x 7 + (2-1) x 1  ; 1,0 2,6
1 1 1 1    = 1 x 8 + (2-1) x 2 + (4-2) x 1  ; 1,0 2,6 4,7
1 1 1 1    = 1 x 9 + (2-1) x 3 + (4-2) x 2  ; 1,0 2,6 4,7 <- new 4,8 == poll 4,7 => Retain 7
1 1        = 1 x 10 + (2-1) x 4             ; 1,0 2,6 <- 2,9
1 1 1      = 1 x 11 + (2-1) x 5 + (3-2) x 1 ; 1,0 2,6 3,10
top = st.pop()
0          =                      5
// -----------------------------------
 * TC: O(m * n) SC: O(m * n)
 * #matrix #monotonic-stack #array #hard
 */

public class FindSortedSubmatricesWithMaximumElementAtMostK {
  
  public long countSubmatrices(int[][] grid, int k) {
    final int m = grid.length, n = grid[0].length;
    long [][]inc = new long[n][m];
    for(int i=0; i<m; i++) inc[n-1][i] = grid[i][n-1]<=k? 1:-1;
    for(int j=n-2; j>=0; j--){
      for(int i=0; i<m; i++){
        if(grid[i][j] > k) {
          inc[j][i] = -1;
        }else if(grid[i][j+1] > k || grid[i][j] < grid[i][j+1]) {
          inc[j][i] = 1;
        }else {
          inc[j][i] = inc[j+1][i]+1;
        }
      }
    }
    long res = 0;
    for(int j=n-1; j>=0; j--){
      // long []hist = new long[m];
      // for(int i=0; i<m; i++) hist[i] = inc[i][j];
      res += helper(inc[j]);
    }
    return res;
  }
  private long helper(long[] A) {

    long[] sum = new long[A.length];
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

    long res = 0;
    for (long s : sum) res += s;

    return res;
  }
}