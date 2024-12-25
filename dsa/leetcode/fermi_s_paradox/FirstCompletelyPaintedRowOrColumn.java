package dsa.leetcode.fermi_s_paradox;

public class FirstCompletelyPaintedRowOrColumn {
  /**
   * https://leetcode.com/problems/first-completely-painted-row-or-column/
   *
   * We process each integer from the array `arr` and paint the corresponding cell in the matrix `mat`. 
   * Using auxiliary arrays to count the number of painted cells in each row and column, 
   * once any row or column is completely painted, return the index at which this condition is met.
   *
   * TC: O(m*n) SC: O(m+n)
   * #array #hash-table #matrix #medium
   */
  
  public int firstCompleteIndex(int[] arr, int[][] mat) {
    final int M = mat.length;
    final int N = mat[0].length;
    // Arrays to count the painted cells in each row and column
    int[] cntRow = new int[M];
    int[] cntCol = new int[N];
    // Reverse mapping from value to position in the matrix
    int [][]rev = new int[M*N][];

    // Build the reverse map from matrix to indices
    for(int i = 0; i < M; i++) {
      for(int j = 0; j < N; j++) {
        rev[mat[i][j]-1] = new int[]{i,j};
      }
    }

    // Iterate over the array, painting cells and checking for completion
    for(int i = 0; i < arr.length; i++) {
      int []id = rev[arr[i]-1];

      cntRow[id[0]]++;
      cntCol[id[1]]++;

      // Check if the current row or column is fully painted
      if(cntRow[id[0]] == N || cntCol[id[1]] == M) return i;
    }
    
    return -1;
  }
}