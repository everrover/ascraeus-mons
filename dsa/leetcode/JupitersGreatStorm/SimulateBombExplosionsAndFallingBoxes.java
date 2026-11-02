package dsa.leetcode.JupitersGreatStorm;

import java.util.*;

/**
 * https://leetcode.com/problems/simulate-bomb-explosions-and-falling-boxes/
 *
 * Simulate until a fixed point. 10^100 seconds is only a stand-in for "forever": a second changes
 * the grid only by destroying a box or moving one down, and each box can do those a bounded number
 * of times. Once a whole second changes nothing, the next one starts from the same grid and so
 * changes nothing either; that grid is the answer.
 * - Explosions must be simultaneous, so destruction is two-phase: mark every box hit by every
 *   triggered bomb against the unchanged grid, then clear the marks. Clearing during the scan would
 *   remove a box another bomb still needs in order to trigger.
 * - Gravity is done in place, bottom row upward. A cell below is empty after the sweep reaches it
 *   exactly when the box there fell or there was none, so a box moves iff it is non-static, and a
 *   whole falling stack moves together. Each box moves at most once per sweep, since it lands on
 *   a row that has already been processed.
 * - A box is not static merely by resting on another box; that box must be static too. The sweep
 *   handles this without computing static-ness: a falling box vacates its cell before the box above
 *   is examined.
 *
 * TC: O(R·mn), R = seconds until stable (R <= O(m²n)) SC: O(mn)
 * #array #matrix #simulation #medium
 */

class SimulateBombExplosionsAndFallingBoxes {
  private static final int[][] ORTHOGONAL = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

  public char[][] finalGrid(char[][] grid) {
        boolean changed = true;
        while (changed) {
            boolean exploded = explode(grid);
            boolean fell = applyGravity(grid);
            changed = exploded || fell;
        }
        return grid;
    }

    private boolean explode(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] destroyed = new boolean[m][n];

        // Mark first, so all explosions use the grid from the start of the second.
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == '*' && touchesBox(grid, r, c)) {
                    markNeighbors(grid, destroyed, r, c);
                }
            }
        }

        boolean any = false;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (destroyed[r][c]) {
                    grid[r][c] = '-';
                    any = true;
                }
            }
        }
        return any;
    }

    private boolean touchesBox(char[][] grid, int r, int c) {
        for (int[] d : ORTHOGONAL) {
            int nr = r + d[0], nc = c + d[1];
            if (inBounds(grid, nr, nc) && grid[nr][nc] == '#') {
                return true;
            }
        }
        return false;
    }

    private void markNeighbors(char[][] grid, boolean[][] destroyed, int r, int c) {
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <= 1; dc++) {
                int nr = r + dr, nc = c + dc;
                if (inBounds(grid, nr, nc) && grid[nr][nc] == '#') {
                    destroyed[nr][nc] = true;
                }
            }
        }
    }

    // Bottom-up: a box's lower neighbor has already moved if it was non-static.
    private boolean applyGravity(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean any = false;
        for (int r = m - 2; r >= 0; r--) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == '#' && grid[r + 1][c] == '-') {
                    grid[r + 1][c] = '#';
                    grid[r][c] = '-';
                    any = true;
                }
            }
        }
        return any;
    }

    private boolean inBounds(char[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }
}
