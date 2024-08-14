package dsa.leetcode.kuiperBelt;

// imports here (if any)

public class SudokuSolver {
  /**
   * https://leetcode.com/problems/sudoku-solver/submissions/
   *
   * Write a program to solve a Sudoku puzzle by filling the empty cells.
   * A sudoku solution must satisfy all of the following rules:
   * - Each of the digits 1-9 must occur exactly once in each row.
   * - Each of the digits 1-9 must occur exactly once in each column.
   * - Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
   * The '.' character indicates empty cells.
   *
   * TC: O(9^(n^2)) SC: O(n^2)
   * #backtracking #matrix #hard
   */

  public boolean isValidInRow(char[][] board, int i, int j, char k) {
    for (int iTmp = 0; iTmp < 9; iTmp++) {
      if (board[iTmp][j] == k) {
        return false;
      }
    }
    return true;
  }

  public boolean isValidInColumn(char[][] board, int i, int j, char k) {
    for (int iTmp = 0; iTmp < 9; iTmp++) {
      if (board[i][iTmp] == k) {
        return false;
      }
    }
    return true;
  }

  public boolean isValidInBlock(char[][] board, int i, int j, char k) {
    int iLt = ((i / 3) * 3) + 3;
    int jLt = ((j / 3) * 3) + 3;
    for (int iTmp = iLt - 3; iTmp < iLt; iTmp++) {
      for (int jTmp = jLt - 3; jTmp < jLt; jTmp++) {
        if (board[iTmp][jTmp] == k) {
          return false;
        }
      }
    }
    return true;
  }

  public boolean isValidSudoku(char[][] board) {
    int i = 0, j = 0;
    for (i = 0; i < 9; i++) {
      for (j = 0; j < 9; j++) {
        if (board[i][j] == '.') {
          char k = '1';
          for (k = '1'; k <= '9'; k++) {
            // Check if placing k is valid in row, column, and block
            boolean status = isValidInRow(board, i, j, k) && isValidInColumn(board, i, j, k) && isValidInBlock(board, i, j, k) && board[i][j] == '.';

            if (status) {
              board[i][j] = k;
              status = isValidSudoku(board);
              if (status) {
                return true;
              } else {
                board[i][j] = '.';
              }
            }
          }
          if (k > '9') {
            return false;
          }
        }
      }
    }
    if (i == 9 && j == 9) return true;
    return false;
  }

  public void solveSudoku(char[][] board) {
    isValidSudoku(board);
  }
}