package dsa.leetcode.mithrim_montez;

public class GridGame {
    /**
     * https://leetcode.com/problems/grid-game/description/?envType=daily-question&envId=2025-01-21
     * 
     * B.F. will take exponential time. 
     * Where for each cell for robo-1, we need to simulate and calculate the score for robo-2 to reach till the same cell.
     *
     * To solve the problem, the first robot minimizes points collected by the second robot by strategically deciding where to move down to the second row. Prefix sums are used for potential paths. Find a point minimizing max points left for the second robot.
     *
     * For 4x2 grid: Path followed by robo-1 is either of these i.e. it drops at a certail point 
     * path_1_sum = (first_row_sum) + (second_row_sum)
     *            = (prefix[i]) + (prefix2[n-1]-prefix2[i-1])
     * x x x x     x x x        x x         x
     *       x         x x        x x x     x x x x
     * 
     * And robo-2 either the above row or the below row(it's playing optimally) i.e. will drop eiher at beginning or the end. 
     * If it doesn't it'll lose some points for sure. 
     * y y y y     y y y        y y         y
     *       y         y y        y y y     y y y y
     * Try overlapping each of the above with each and we can see... and 
     * path_2_sum = max(
     *           (remaining score at idx `i` in first row if robo-1 drops at idx `i`), 
     *           (remaining score at idx `i` in second row if robo-1 drops at idx `i`)
     * ) = max(
     *           (prefix[n-1]-prefix[i]),
     *           (prefix2[i-1])
     * )
     * robo-1 will definitely minimize the score of robo-2. So we pick min(path_2_sum) for all possible paths of robo-1.
     * 
     * TC: O(n) SC: O(n)
     * #array #matrix #prefix-sum #medium
     */
    
    public long gridGame(int[][] grid) {
        if(grid.length == 0) return 0;
        final int m = grid.length, n = grid[0].length;
        long []pre = new long[n];
        long []pre2 = new long[n];
        for(int i=0; i<n; i++){
            pre[i] = grid[0][i]+((i>0)?pre[i-1]:0);
            pre2[i] = grid[1][i]+((i>0)?pre2[i-1]:0);
        }
        long maxpath1 = 0, res = Long.MAX_VALUE;
        for(int i=0; i<n; i++){
            // PATH taken by robo-1
            long path1 = 0L+pre[i]+pre2[n-1]-(i>0?pre2[i-1]:0);
            // PATH taken by robo-2
            long path2 = Math.max(
                0L+pre[n-1]-pre[i],
                0L+(i>0?pre2[i-1]:0)
            );
            res = Math.min(res, path2);
        }
        return res;
    }
}