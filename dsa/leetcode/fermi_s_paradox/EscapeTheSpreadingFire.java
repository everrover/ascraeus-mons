package dsa.leetcode.fermi_s_paradox;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class EscapeTheSpreadingFire {

    /**
     * https://leetcode.com/problems/escape-the-spreading-fire/
     *
     * > binary-search approach
   * Thought of applying the approach used in LastDayWhereYouCanStillCross.java.
   * Performing repetitive BFS for the entire solution space using binary search.
   * But found a more optimal way. 
   * 
   * TC: O(m*n*log(m*n)) SC: O(m*n)
   * 
   * > optimal approach
   * Will perform the BFS once for finding the min time when fire reaches a cell.
   * And then again for finding the min time when person reaches a cell. 
   * For finding the min time when person reaches a cell, we check if fire has reached that cell before min-reach time of that person.
   * Applied the edge-cases afterwards. If cell[m-1][n-1] is not reachable by person, return -1.
   * If fire never reaches cell[m-1][n-1], return 10^9.
   * Last edge case is tricky. 
   * There are two ways where we reach cell[m-1][n-1], either from cell[m-1][n-2] or cell[m-2][n-1].
   * 
   * If fire is trailing reaches cell[m-1][n-2] or cell[m-2][n-1] before person, 
   * then we need to return 1 less than the min time when person reaches cell[m-1][n-1].
   * And that is calculated by finding the difference between the 
   * min time when person and fire reaches cell[m-1][n-2] or cell[m-2][n-1]. 
   * If the difference is greater than min time between the difference of person and fire reaching cell[m-1][n-1], then we return this difference.
   * 
     *
     * TC: O(m*n) SC: O(m*n)
   * 
   * #bfs #binary-search #optimal #binary-search-over-solution-space
     */
    
    private int [][]moves = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
  public int maximumMinutes(int[][] grid) {
    final int m = grid.length, n = grid[0].length;
    int [][]mark = new int[m][n];
    int [][]markPerson = new int[m][n];
    Queue<int[]> q = new LinkedList<>();
    for(int i=0; i<m; i++){
      for(int j=0; j<n; j++){
        if(grid[i][j] == 1) q.offer(new int[]{i,j,0});
      }
    }
    for(int mk[]: mark) Arrays.fill(mk, (int)(Integer.MAX_VALUE));
    for(int mk[]: markPerson) Arrays.fill(mk, (int)(Integer.MAX_VALUE));
    while(!q.isEmpty()){
      int []pts = q.poll();
      if(mark[pts[0]][pts[1]] < pts[2]) continue;
      mark[pts[0]][pts[1]] = pts[2];
      for(int []move: moves){
        int x = pts[0]+move[0], y = pts[1]+move[1], reach = pts[2]+1;
        if(isValid(x,y,m,n) && grid[x][y] != 2) {
          q.offer(new int[]{x, y, reach});
        }
      }
    }
    q.offer(new int[]{0,0,0});
    while(!q.isEmpty()){
      int []pts = q.poll();
      int i=pts[0], j=pts[1], k=pts[2];
      if(markPerson[i][j] < pts[2] || ((i!=m-1 || j!=n-1) && mark[i][j]<k) || (i==m-1 && j==n-1 && mark[i][j]<k)) continue;
      markPerson[i][j] = k;
      for(int []move: moves) {
        int x = i+move[0], y = j+move[1], reach = pts[2]+1;
        if(isValid(x,y,m,n) && grid[x][y] != 2) {
          q.offer(new int[]{x, y, reach});
        }
      }
    }
    
    if(markPerson[m-1][n-1]==Integer.MAX_VALUE) return -1;
    if(mark[m-1][n-1]==Integer.MAX_VALUE) return (int)1e9;
    if(mark[m-1][n-1] < markPerson[m-1][n-1]) return -1;

    int res = mark[m-1][n-1]-markPerson[m-1][n-1];
    int diff1 = Math.abs(mark[m-1][n-2] - markPerson[m-1][n-2]), diff2 = Math.abs(mark[m-2][n-1] - markPerson[m-2][n-1]);
    if(diff1>res || diff2>res) return res;
    return res-1;
  }

  private boolean isValid(int x, int y, int m, int n){
    return (x>=0 && y>=0 && x<m && y<n);
  }
}