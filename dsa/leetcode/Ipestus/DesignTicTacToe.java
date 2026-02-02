package dsa.leetcode.Ipestus;

public class DesignTicTacToe {
    int[] rowsum;
    int[] colsum;
    int mdsum;
    int sdsum;
    int isV = 0;

designTicTacToe(int n) {
        rowsum = new int[n];
        colsum = new int[n];
        mdsum = 0;
        sdsum = 0;
    }

    /**
     * Moves a player to the given row and column.
     * Checks for wins along the row, column, and diagonals.
     *
     * TC: O(1) per move
     *
     * #design #matrix #simulation #medium
     */
    public int move(int row, int col, int player) {
        if (isV != 0) return isV;
        rowsum[row] += (player == 1 ? 1 : -1);
        colsum[col] += (player == 1 ? 1 : -1);
        if (row == col) mdsum += (player == 1 ? 1 : -1);
        if (row + col == rowsum.length - 1) sdsum += (player == 1 ? 1 : -1);
        if (Math.abs(rowsum[row]) == rowsum.length || Math.abs(colsum[col]) == rowsum.length || Math.abs(mdsum) == rowsum.length || Math.abs(sdsum) == rowsum.length) {
            isV = player;
        }
        return isV;
    }
}