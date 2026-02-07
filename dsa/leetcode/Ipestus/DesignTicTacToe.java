package dsa.leetcode.Ipestus;

public class DesignTicTacToe {
    int[] rowsum;
    int[] colsum;
    int mdsum;
    int sdsum;
    int isV = 0;

    public DesignTicTacToe(int n) {
        rowsum = new int[n];
        colsum = new int[n];
        mdsum = sdsum = isV = 0;
    }

    /**
     * Moves a player to the given row and column.
     * Checks for wins along the row, column, and diagonals.
     * 
     * The valid conditions for a win are:
     * 1. All values in a row are the same.
     * 2. All values in a column are the same.
     * 3. All values in the main diagonal are the same.
     * 4. All values in the secondary diagonal are the same.
     * 
     * Keeping track of the sums for each row, column, and diagonal allows for checking if a player has won in
     * O(1) time complexity per move.
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