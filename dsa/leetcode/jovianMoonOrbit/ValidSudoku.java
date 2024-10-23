package dsa.leetcode.jovianMoonOrbit;

public class ValidSudoku {

    /**
     * https://leetcode.com/problems/valid-sudoku/solution/
     *
     * This solution checks the validity of a partially filled Sudoku board by ensuring that each digit appears only once
     * in each row, column, and 3x3 sub-box. We utilize three boolean arrays to track the presence of digits in rows,
     * columns, and sub-boxes, returning false if any repetition is detected.
     *
     * TC: O(n^2) SC: O(n^2)
     * #array #hash-table #matrix #medium
     */

    public boolean isValidSudoku(char[][] board) {
        int N = 9;

        boolean[][] rows = new boolean[N][N]; // can use bitmasks here as well
        boolean[][] cols = new boolean[N][N];
        boolean[][] boxes = new boolean[N][N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (board[r][c] == '.') {
                    continue;
                }
                int pos = board[r][c] - '1';
                int idx = (r / 3) * 3 + c / 3;

                // Check if the number is already seen in the current row, column, or box
                if (rows[r][pos] || cols[c][pos] || boxes[idx][pos]) {
                    return false;
                }

                // Mark the number as seen
                boxes[idx][pos] = rows[r][pos] = cols[c][pos] = true;
            }
        }
        return true;
    }
}