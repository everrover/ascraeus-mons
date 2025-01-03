package dsa.leetcode.fermi_s_paradox;

import java.util.LinkedList;
import java.util.Queue;

/*
 * https://leetcode.com/problems/battleships-in-a-board/description/
 *
 * A new battleship is encountered only if the current cell is 'X' and it is not
 * adjacent to another 'X' above or to the left. IF we're going from left to 
 * right(0->n) and top to bottom(0->m). Since if we're going from right to left
 * and bottom to top, we would have already counted the battleship if it's marked as 'X'.
 * 
 * BFS/DFS also works but the above one is also optimal.
 * 
 * TC: O(m * n) SC: O(1)
 * #array #matrix #medium #bfs #dfs
 */

public class BattleshipsInABoard {
int m, n;
  public int countBattleships(char[][] board) {
    int res = 0;
    m = board.length; n = board[0].length;
    // boolean [][]v = new boolean[m][n];
    for(int i=0; i<m; i++){
      for(int j=0; j<n; j++){
        if (board[i][j] == 'X' && (i == 0 || board[i - 1][j] != 'X') && (j == 0 || board[i][j - 1] != 'X')) {
          res++;
        }
        // if(!v[i][j]){
        //   if(board[i][j] == 'X'){
        //     res++;
        //     bfs(board, v, i, j);
        //   } else v[i][j] = true;
        // }
      }
    }
      
    return res;
  }
  
  private void bfs(char [][]b, boolean [][]v, int idx, int jdx){
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[]{idx, jdx});
    while(!q.isEmpty()){
      int []c = q.poll();
      
      if(v[c[0]][c[1]]) continue;
      v[c[0]][c[1]] = true;
      if(b[c[0]][c[1]] != 'X') continue;
      if(c[0] != m-1) q.offer(new int[]{c[0]+1, c[1]});
      if(c[0] != 0) q.offer(new int[]{c[0]-1, c[1]});
      if(c[1] != n-1) q.offer(new int[]{c[0], c[1]+1});
      if(c[1] != 0) q.offer(new int[]{c[0], c[1]-1});
    }
  }
}