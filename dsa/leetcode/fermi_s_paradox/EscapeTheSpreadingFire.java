package dsa.leetcode.fermi_s_paradox;

public class EscapeTheSpreadingFire {

    /**
     * https://leetcode.com/problems/escape-the-spreading-fire/
     *
     * We utilize multi-source BFS to calculate the earliest time the fire can reach each cell.
     * Applying a binary search helps to find the maximum time we can remain at the starting point safely.
     * If the fire intercepts the shortest path, reaching the safehouse becomes impossible.
     *
     * TC: O(m * n * log(max(m, n))) SC: O(m * n)
     * #bfs #binary-search #matrix #hard
     */
    
    public int maxMinutes(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // Assuming mark and markPerson arrays are calculated somewhere in the code
        int res = mark[m-1][n-1] - markPerson[m-1][n-1];
        int diff1 = Math.abs(mark[m-1][n-2] - markPerson[m-1][n-2]),
            diff2 = Math.abs(mark[m-2][n-1] - markPerson[m-2][n-1]);
        if (diff1 > res || diff2 > res) return res;
        return res - 1;
    }

    private boolean isValid(int x, int y, int m, int n) {
        return (x >= 0 && y >= 0 && x < m && y < n);
    }
}