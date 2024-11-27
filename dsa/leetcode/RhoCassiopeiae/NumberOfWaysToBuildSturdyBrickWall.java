package dsa.leetcode.RhoCassiopeiae;

import java.util.*;

/**
 * https://leetcode.com/problems/number-of-ways-to-build-sturdy-brick-wall/
 *
 * First we need to find all possible configurations of the bricks in each row. For that we've used 
 * `dfs_bricks_row`.
 * 
 * For each row transition, we can transition to next state only if `current_mask & previous_mask == 0`,
 * otherwise the sturdy constraint is violated. Hence the DFS. We use memoization to store
 * repeated states.
 * 
 *
 * TC: O(height * 2^width * numberOfConfigurations) SC: O(height * 2^width)
 * #array #dynamic-programming #bitmask #medium
 */
public class NumberOfWaysToBuildSturdyBrickWall {
    final int MOD = (int)(1e9+7);
    int [][]dp = new int[101][1024];

    private void dfs_bricks_row(int w, int width, int []bricks, int mask, List<Integer> masks) {
        if (w == width) 
            masks.add(mask);
        else {
            if (w != 0) mask += (1 << (w - 1));
            for (int b : bricks) if (w + b <= width)
                dfs_bricks_row(w + b, width, bricks, mask, masks);
        }
    }

    private int dfs(int h, int prevmask, List<Integer> masks) {
        if (h == 0)
            return 1;
        if (dp[h][prevmask] != -1) return dp[h][prevmask];

        int res = 0;
        for (int mask : masks) if ((mask & prevmask) == 0)
            res = (res + dfs(h - 1, mask, masks)) % MOD;
        return dp[h][prevmask] = res;
    }

    public int buildWall(int height, int width, int[] bricks) {
        int res = 0;
        for(int []d: dp) Arrays.fill(d, -1);
        List<Integer> masks = new ArrayList<>();
        dfs_bricks_row(0, width, bricks, 0, masks);
        return dfs(height, 0, masks);
    }
}