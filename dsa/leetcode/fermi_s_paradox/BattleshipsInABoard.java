package dsa.leetcode.fermi_s_paradox;

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
 * #array #matrix #medium
 */

public class BattleshipsInABoard {

    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length;
        int res = 0;
        // Iterate through each cell of the board
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Increment res if it's the start of a battleship
                if (board[i][j] == 'X' && (i == 0 || board[i - 1][j] != 'X') && (j == 0 || board[i][j - 1] != 'X')) {
                    res++;
                }
            }
        }
        return res;
    }
}